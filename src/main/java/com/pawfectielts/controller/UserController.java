package com.pawfectielts.controller;

import com.pawfectielts.dto.ChangePasswordRequest;
import com.pawfectielts.entity.User;
import com.pawfectielts.repositories.UserRepository;
import com.pawfectielts.service.impl.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImplement userServiceImplement;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Set the creation date
        user.setCreate_at(new Date());
        user.setRole("USER");

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // You might want to perform additional validation and checks here before saving the user
        User savedUser = userServiceImplement.register(user);

        if (savedUser != null) {
            return "User registered successfully!";
        } else {
            return "User registration failed.";
        }
    }

    @PutMapping("/change-password/{userId}")
    public String changePassword(
            @PathVariable Long userId,
            @RequestBody ChangePasswordRequest changePasswordRequest) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "User not found.";
        }

        // Check if the provided current password matches the stored hashed password
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            return "Current password is incorrect.";
        }

        // Hash the new password and update the user's password
        String hashedNewPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPassword(hashedNewPassword);
        userRepository.save(user);

        return "Password changed successfully.";
    }

}