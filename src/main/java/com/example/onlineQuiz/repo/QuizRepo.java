package com.example.onlineQuiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineQuiz.model.Quiz;
import com.example.onlineQuiz.model.User;

public interface QuizRepo extends JpaRepository<Quiz, Long>{
    List<Quiz> findByCreatedBy(User teacher);
}
