package com.example.onlineQuiz;

import com.example.onlineQuiz.model.*;
import com.example.onlineQuiz.repo.*;
import com.example.onlineQuiz.service.SubmissionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubmissionServiceTest {

    @Mock
    private QuestionRepo questionRepo;

    @Mock
    private SubmissionRepo submissionRepo;

    @Mock
    private QuizRepo quizRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private SubmissionService submissionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitQuiz_shouldCalculateCorrectScore() {

        User student = new User();
        student.setEmail("student1@quiz.com");

        Quiz quiz = new Quiz();
        quiz.setId(1L);

        Question q1 = new Question();
        q1.setId(1L);
        q1.setCorrectOption('A');

        Question q2 = new Question();
        q2.setId(2L);
        q2.setCorrectOption('B');

        Map<Long, Character> answers = new HashMap<>();
        answers.put(1L, 'A'); // correct
        answers.put(2L, 'C'); // wrong

        when(userRepo.findByEmail("student1@quiz.com"))
                .thenReturn(Optional.of(student));

        when(quizRepo.findById(1L))
                .thenReturn(Optional.of(quiz));

        when(submissionRepo.findByQuizAndStudent(quiz, student))
                .thenReturn(Optional.empty());

        when(questionRepo.findByQuiz(quiz))
                .thenReturn(List.of(q1, q2));

        when(submissionRepo.save(any(Submission.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Submission result = submissionService
                .submitQuiz(1L, answers, "student1@quiz.com");

        assertEquals(1, result.getScore());
    }

    @Test
    void submitQuiz_shouldThrowException_ifAlreadyAttempted() {

        User student = new User();
        Quiz quiz = new Quiz();

        when(userRepo.findByEmail("student1@quiz.com"))
                .thenReturn(Optional.of(student));

        when(quizRepo.findById(1L))
                .thenReturn(Optional.of(quiz));

        when(submissionRepo.findByQuizAndStudent(quiz, student))
                .thenReturn(Optional.of(new Submission()));

        assertThrows(RuntimeException.class,
                () -> submissionService.submitQuiz(1L, new HashMap<>(), "student1@quiz.com"));
    }

    @Test
    void submitQuiz_shouldThrowException_ifStudentNotFound() {

        when(userRepo.findByEmail("student1@quiz.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> submissionService.submitQuiz(1L, new HashMap<>(), "student1@quiz.com"));
    }
}