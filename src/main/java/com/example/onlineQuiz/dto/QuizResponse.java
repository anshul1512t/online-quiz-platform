package com.example.onlineQuiz.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class QuizResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;

    private List<QuestionResponse> questions;
}
