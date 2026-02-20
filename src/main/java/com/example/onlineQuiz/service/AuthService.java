package com.example.onlineQuiz.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlineQuiz.dto.RegisterRequest;
import com.example.onlineQuiz.model.User;
import com.example.onlineQuiz.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest userRequest) {
        if (userRepo.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepo.save(user);
    }
}
