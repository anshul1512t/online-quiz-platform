package com.example.onlineQuiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.onlineQuiz.model.Question;
import com.example.onlineQuiz.model.Quiz;
import com.example.onlineQuiz.model.User;
import com.example.onlineQuiz.repo.QuestionRepo;
import com.example.onlineQuiz.repo.QuizRepo;
import com.example.onlineQuiz.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepository;
    private final QuestionRepo questionRepository;
    private final UserRepo userRepository;

    public Quiz createQuiz(String title, String description, String teacherEmail) {

        User teacher = userRepository.findByEmail(teacherEmail)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Quiz quiz = Quiz.builder()
                .title(title)
                .description(description)
                .createdBy(teacher)
                .build();

        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
    return quizRepository.findAll();
}

    public Question addQuestion(Long quizId, Question question) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        question.setQuiz(quiz);
        return questionRepository.save(question);
    }
}