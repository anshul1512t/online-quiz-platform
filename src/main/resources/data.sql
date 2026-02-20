-- =========================
-- USERS
-- password: password123 (BCrypt encoded)
-- =========================

INSERT INTO users (id, username, email, password, role, created_at) VALUES
(1, 'admin', 'admin@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'ADMIN', NOW()),
(2, 'teacher1', 'teacher1@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'TEACHER', NOW()),
(3, 'teacher2', 'teacher2@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'TEACHER', NOW()),
(4, 'student1', 'student1@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'STUDENT', NOW()),
(5, 'student2', 'student2@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'STUDENT', NOW()),
(6, 'student3', 'student3@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'STUDENT', NOW()),
(7, 'student4', 'student4@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'STUDENT', NOW()),
(8, 'student5', 'student5@quiz.com', '$2a$10$7QJYF5QnQh6XG4cS9F1BPO5vQpG1z1dZl8F5HkF3c8L6jDkJvR5zO', 'STUDENT', NOW());

-- =========================
-- QUIZZES
-- =========================

INSERT INTO quiz (id, title, description, created_at, updated_at, teacher_id) VALUES
(1, 'Java Basics', 'Core Java fundamentals quiz', NOW(), NOW(), 2),
(2, 'Spring Boot Intro', 'Spring Boot concepts quiz', NOW(), NOW(), 2),
(3, 'Database Fundamentals', 'MySQL basics quiz', NOW(), NOW(), 3);

-- =========================
-- QUESTIONS
-- =========================

INSERT INTO question
(id, question_text, optiona, optionb, optionc, optiond, correct_option, quiz_id)
VALUES
(1, 'Which keyword is used for inheritance?', 'extends', 'implements', 'inherit', 'super', 'A', 1),
(2, 'Which collection allows duplicates?', 'Set', 'Map', 'List', 'TreeSet', 'C', 1),
(3, 'JVM stands for?', 'Java Virtual Machine', 'Java Variable Method', 'Joint VM', 'None', 'A', 1),
(4, 'Spring Boot starter reduces?', 'Configuration', 'Security', 'Database', 'Nothing', 'A', 2),
(5, 'Default port of Spring Boot?', '8080', '3306', '9090', '3000', 'A', 2),
(6, '@RestController combines?', '@Controller + @ResponseBody', '@Service + @Component', 'Nothing', 'All', 'A', 2),
(7, 'Primary key must be?', 'Unique', 'Null', 'Duplicate', 'Optional', 'A', 3),
(8, 'SQL stands for?', 'Structured Query Language', 'Simple Query Lang', 'System Query', 'None', 'A', 3),
(9, 'Which is DML?', 'SELECT', 'CREATE', 'DROP', 'ALTER', 'A', 3),
(10, 'Which keyword for polymorphism?', 'override', 'overload', 'both', 'none', 'C', 1),
(11, 'Dependency Injection is?', 'Design pattern', 'DB concept', 'Security type', 'None', 'A', 2),
(12, 'Which command deletes table?', 'DROP', 'DELETE', 'REMOVE', 'CLEAR', 'A', 3);

-- =========================
-- SUBMISSIONS
-- =========================

INSERT INTO submission (id, student_id, quiz_id, score, submitted_at) VALUES
(1, 4, 1, 3, NOW()),
(2, 5, 1, 2, NOW()),
(3, 4, 2, 3, NOW()),
(4, 6, 3, 2, NOW());