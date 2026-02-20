package com.example.onlineQuiz.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class QuizCreate {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}