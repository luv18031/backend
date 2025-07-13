package com.music.love.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.love.app.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 