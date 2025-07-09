package com.music.app.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.backend.dto.RegistrationRequestDto;
import com.music.app.backend.dto.RegistrationResponseDto;
import com.music.app.backend.mapper.UserRegistrationMapper;
import com.music.app.backend.service.registration.UserRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper userRegistrationMapper;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(
      @Valid @ModelAttribute final RegistrationRequestDto registrationDTO) {

        final var registeredUser = userRegistrationService
          .registerUser(userRegistrationMapper.toEntity(registrationDTO));

        return ResponseEntity.ok(
          userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }

}