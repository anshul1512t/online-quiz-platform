package com.example.onlineQuiz.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineQuiz.model.User;


public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);
}
