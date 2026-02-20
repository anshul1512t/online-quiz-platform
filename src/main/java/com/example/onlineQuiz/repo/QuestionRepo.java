package com.example.onlineQuiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineQuiz.model.Question;
import com.example.onlineQuiz.model.Quiz;



public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findByQuiz(Quiz quiz);
} 