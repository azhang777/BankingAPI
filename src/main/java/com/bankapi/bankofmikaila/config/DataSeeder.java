package com.bankapi.bankofmikaila.config;

import com.bankapi.bankofmikaila.enumeration.Role;
import com.bankapi.bankofmikaila.model.AppUser;
import com.bankapi.bankofmikaila.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final String adminPassword;
    private final String userPassword;

    public DataSeeder(UserRepository userRepository,
                      PasswordEncoder encoder,
                      @Value("${app.admin.password}") String adminPassword,
                      @Value("${app.user.password}") String userPassword) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.adminPassword = adminPassword;
        this.userPassword = userPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        seed("admin",  Role.ADMIN, adminPassword);
        seed("user", Role.USER, userPassword);
    }

    private void seed(String username, Role role,  String rawPassword) {
        if (userRepository.findByUsername(username).isEmpty()) {
            userRepository.save(new AppUser(username, role ,encoder.encode(rawPassword)));
        }
    }
}
