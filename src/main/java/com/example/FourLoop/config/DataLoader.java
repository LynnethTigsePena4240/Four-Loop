package com.example.FourLoop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.FourLoop.Model.User;
import com.example.FourLoop.Repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin123");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRole("ADMIN");
            userRepository.save(admin);

            User staff = new User();
            staff.setUsername("staff123");
            staff.setPassword(passwordEncoder.encode("staffpass"));
            staff.setRole("STAFF");
            userRepository.save(staff);

            User user = new User();
            user.setUsername("user123");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.setRole("USER");
            userRepository.save(user);
        }
    }
}