package com.example.onlineQuiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineQuiz.model.Question;
import com.example.onlineQuiz.model.Quiz;
import com.example.onlineQuiz.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quiz")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz,
            @AuthenticationPrincipal UserDetails user) {

        Quiz created = quizService.createQuiz(
                quiz.getTitle(),
                quiz.getDescription(),
                user.getUsername());

        return ResponseEntity.ok(created);
    }

    @PostMapping("/quiz/{quizId}/question")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addQuestion(@PathVariable Long quizId,
            @RequestBody Question question) {

        return ResponseEntity.ok(
                quizService.addQuestion(quizId, question));
    }

    @GetMapping("/quizzes")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }
}