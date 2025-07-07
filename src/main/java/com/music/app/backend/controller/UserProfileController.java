package com.music.app.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.backend.dto.UserProfileDto;
import com.music.app.backend.mapper.UserMapper;
import com.music.app.backend.service.UserService;

import lombok.RequiredArgsConstructor;

// UserProfileController.java
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;
     

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUserProfile(
      final Authentication authentication) {
  
        final var user = 
          userService.getUserByUsername(authentication.getName());

        return ResponseEntity.ok(userMapper.toUserProfileDto(user));
    }
}
