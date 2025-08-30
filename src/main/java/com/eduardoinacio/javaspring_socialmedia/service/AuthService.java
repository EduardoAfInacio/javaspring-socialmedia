package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.Auth.LoginResponse;
import com.eduardoinacio.javaspring_socialmedia.entity.Role;
import com.eduardoinacio.javaspring_socialmedia.entity.User;
import com.eduardoinacio.javaspring_socialmedia.repository.RoleRepository;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtEncoder jwtEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponse login(String email, String password){
        var user = userRepository.findByEmail(email);

        if(user.isEmpty() || !user.get().passwordVerification(password, passwordEncoder)){
            throw new BadCredentialsException("Email/Password invalid or user does not exist");
        }

        var now = Instant.now();
        var expiresIn = now.plusSeconds(900);

        var claims = JwtClaimsSet
                .builder()
                .issuer("Spring Social Media")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(expiresIn)
                .build();

        var tokenEncoded = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(tokenEncoded, expiresIn.getEpochSecond());
    }

    @Transactional
    public Boolean register(String name,String email, String password){
        var user = userRepository.findByEmail(email);

        if(user.isPresent()){
            throw new BadCredentialsException("Email already exists");
        }

        var role = roleRepository.findByName(Role.Values.BASIC.name());
        var newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(Set.of(role));

        userRepository.save(newUser);

        return true;
    }
}
