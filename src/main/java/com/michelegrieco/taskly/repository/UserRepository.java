package com.michelegrieco.taskly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelegrieco.taskly.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
