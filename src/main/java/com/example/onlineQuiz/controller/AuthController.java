package com.example.onlineQuiz.controller;

import com.example.onlineQuiz.dto.AuthRequest;
import com.example.onlineQuiz.dto.AuthResponse;
import com.example.onlineQuiz.dto.RegisterRequest;
import com.example.onlineQuiz.service.AuthService;
import com.example.onlineQuiz.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest userRequest) {
        authService.registerUser(userRequest);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

}
