package com.eduardoinacio.javaspring_socialmedia.config;

import com.eduardoinacio.javaspring_socialmedia.entity.Role;
import com.eduardoinacio.javaspring_socialmedia.entity.User;
import com.eduardoinacio.javaspring_socialmedia.repository.RoleRepository;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private StringEncryptor stringEncryptor;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, StringEncryptor stringEncryptor) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByEmail("admin@email.com");

        userAdmin.ifPresentOrElse(
                user -> { System.out.println("Admin user already exists"); },
                () -> {
                    var admin = new User();
                    admin.setDocument(stringEncryptor.encrypt("12345678910"));
                    admin.setName("Admin");
                    admin.setEmail("admin@email.com");
                    admin.setBirthDate(LocalDate.now().minusYears(30));
                    admin.setPassword(passwordEncoder.encode("123"));
                    admin.setRoles(Set.of(roleAdmin));
                    userRepository.save(admin);
                }
        );
    }
}
