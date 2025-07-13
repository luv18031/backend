package com.music.love.app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.love.app.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, UUID> {
    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 