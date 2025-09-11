package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.LoginResponse;
import com.eduardoinacio.javaspring_socialmedia.entity.Role;
import com.eduardoinacio.javaspring_socialmedia.entity.User;
import com.eduardoinacio.javaspring_socialmedia.repository.RoleRepository;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import com.eduardoinacio.javaspring_socialmedia.service.Mail.MailDTO;
import jakarta.transaction.Transactional;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtEncoder jwtEncoder;
    private StringEncryptor stringEncryptor;
    private RedisService redisService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder, StringEncryptor stringEncryptor, RedisService redisService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.stringEncryptor = stringEncryptor;
        this.redisService = redisService;
    }

    public LoginResponse login(String email, String password){
        var user = userRepository.findByEmail(email);

        if(user.isEmpty() || !user.get().passwordVerification(password, passwordEncoder)){
            throw new BadCredentialsException("Email/Password invalid or user does not exist");
        }

        var now = Instant.now();
        var expiresIn = now.plusSeconds(900);

        var scope = user.get().getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet
                .builder()
                .issuer("Spring Social Media")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(expiresIn)
                .claim("scope", scope)
                .build();

        var tokenEncoded = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(tokenEncoded, expiresIn.getEpochSecond());
    }

    @Transactional
    public void register(String document, String name, String email, LocalDate birthDate, String password){

        if(userRepository.existsByEmail(email)){
            throw new BadCredentialsException("Email already registered");
        }

        if(userRepository.existsByDocument(stringEncryptor.encrypt(document))){
            throw new BadCredentialsException("Document already registered");
        }

        var role = roleRepository.findByName(Role.Values.BASIC.name());
        var newUser = new User();
        newUser.setDocument(stringEncryptor.encrypt(document));
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setBirthDate(stringEncryptor.encrypt(birthDate.toString()));
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(Set.of(role));

        userRepository.save(newUser);

        redisService.pushEmail(new MailDTO(
                email,
                "Socialmedia - Account created successfully",
                "Congratulations, your account has been created successfully.",
                0
        ));
    }
}
