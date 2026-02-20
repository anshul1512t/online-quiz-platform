package com.example.onlineQuiz.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.onlineQuiz.model.Question;
import com.example.onlineQuiz.model.Quiz;
import com.example.onlineQuiz.model.Submission;
import com.example.onlineQuiz.model.User;
import com.example.onlineQuiz.repo.QuestionRepo;
import com.example.onlineQuiz.repo.QuizRepo;
import com.example.onlineQuiz.repo.SubmissionRepo;
import com.example.onlineQuiz.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final QuestionRepo questionRepository;
    private final SubmissionRepo submissionRepository;
    private final QuizRepo quizRepository;
    private final UserRepo userRepository;

    @Transactional
    public Submission submitQuiz(Long quizId,
            Map<Long, Character> answers,
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow();

        Optional<Submission> existing = submissionRepository.findByQuizAndStudent(quiz, student);

        if (existing.isPresent()) {
            throw new RuntimeException("You already attempted this quiz");
        }

        List<Question> questions = questionRepository.findByQuiz(quiz);

        int score = 0;

        for (Question q : questions) {
            Character studentAnswer = answers.get(q.getId());
            if (studentAnswer != null &&
                    q.getCorrectOption().equals(studentAnswer)) {
                score++;
            }
        }

        Submission submission = Submission.builder()
                .quiz(quiz)
                .student(student)
                .score(score)
                .build();

        return submissionRepository.save(submission);
    }

    public List<Submission> getMySubmissions(String email) {
        User student = userRepository.findByEmail(email).orElseThrow();
        return submissionRepository.findByStudent(student);
    }
}