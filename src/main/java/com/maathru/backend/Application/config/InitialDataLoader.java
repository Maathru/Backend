package com.maathru.backend.Application.config;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitialDataLoader {

    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            List<User> users = List.of(
                    new User(1L, "admin@maathru.com", "Admin", "Buddhika", Role.ADMIN),
                    new User(2L, "doctor@maathru.com", "Doctor", "Buddhika", Role.DOCTOR),
                    new User(3L, "midwife@maathru.com", "Midwife", "Buddhika", Role.MIDWIFE),
                    new User(4L, "parent@maathru.com", "Parent", "Buddhika", Role.PARENT),
                    new User(5L, "eligible@maathru.com", "Eligible", "Buddhika", Role.ELIGIBLE),
                    new User(6L, "pending@maathru.com", "Pending", "Buddhika", Role.PENDING),
                    new User(7L, "user@maathru.com", "User", "Buddhika", Role.USER)
            );

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            for (User user : users) {
                if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                    user.setAccountNonExpired(true);
                    user.setAccountNonLocked(true);
                    user.setCreatedAt(LocalDateTime.now());
                    user.setEnabled(true);
                    user.setLoginAttempts(0);
                    user.setPassword(passwordEncoder.encode("zaq123"));
                    user.setUpdatedAt(LocalDateTime.now());
                    user.setCreatedBy(user);
                    user.setUpdatedBy(user);
                    userRepository.save(user);
                    log.info("{} user created successfully.", user.getFirstName());
                } else {
                    log.warn("{} user already exists.", user.getFirstName());
                }
            }
        };
    }
}