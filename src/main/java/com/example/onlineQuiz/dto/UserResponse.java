package com.example.onlineQuiz.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}