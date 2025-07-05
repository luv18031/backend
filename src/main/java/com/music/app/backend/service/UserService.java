package com.music.app.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.music.app.backend.entity.MyUser;
import com.music.app.backend.repository.MyUserRepository;

import lombok.RequiredArgsConstructor;

// UserService.java
@Service
@RequiredArgsConstructor
public class UserService {

    private final MyUserRepository userRepository;

    public MyUser getUserByUsername(final String username) {
        return userRepository.findByUsername(username)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.GONE, 
              "The user account has been deleted or inactivated"));
    }
}