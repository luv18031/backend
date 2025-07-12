package com.music.love.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.love.app.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    
} 