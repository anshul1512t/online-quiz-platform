package com.example.onlineQuiz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineQuiz.model.Quiz;
import com.example.onlineQuiz.model.Submission;
import com.example.onlineQuiz.model.User;

public interface SubmissionRepo extends JpaRepository<Submission, Long> {

    boolean existsByStudentAndQuiz(User student, Quiz quiz);

    List<Submission> findByStudent(User student);

    Optional<Submission> findByQuizAndStudent(Quiz quiz, User student);

}