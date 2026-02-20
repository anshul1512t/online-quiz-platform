package com.example.onlineQuiz.dto;


import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmissionRequest {

    @NotNull
    private Long quizId;

    // questionId -> selectedOption
    private Map<Long, Character> answers;
}