package com.example.FourLoop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FourLoop.Model.User;
import com.example.FourLoop.Repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            return "redirect:/register?error=UsernameTaken";
        }

        User user = new User();
        user.setUsername(username);
        
        String encodedPass = passwordEncoder.encode(password);
        user.setPassword(encodedPass);
        
        userRepo.save(user);

        return "redirect:/login?registered";
    }
}