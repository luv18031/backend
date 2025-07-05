package com.music.app.backend.service.registration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.music.app.backend.entity.MyUser;
import com.music.app.backend.repository.MyUserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

// UserRegistrationService.java
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

  private final MyUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public MyUser registerUser(MyUser request) {
      if (userRepository.existsByUsername(request.getUsername()) || 
          userRepository.existsByEmail(request.getEmail())) {

          throw new ValidationException(
            "Username or Email already exists");
      }

      MyUser user = new MyUser();
      user.setUsername(request.getUsername());
      user.setEmail(request.getEmail());
      user.setPassword(passwordEncoder.encode(request.getPassword()));

      return userRepository.save(user);
  }
}