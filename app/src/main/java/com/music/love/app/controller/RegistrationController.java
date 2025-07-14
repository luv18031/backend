package com.music.love.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.RegistrationRequestDto;
import com.music.love.app.dto.RegistrationResponseDto;
import com.music.love.app.mapper.UserRegistrationMapper;
import com.music.love.app.repository.UserRepository;
import com.music.love.app.service.UserRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {
    
    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper userRegistrationMapper;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(@Valid @RequestBody final RegistrationRequestDto registrationRequestDto) {
        
        if(userRepository.existsByUsername(registrationRequestDto.username()) ||
         userRepository.existsByEmail(registrationRequestDto.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        final var registeredUser = userRegistrationService.registerUser(userRegistrationMapper.toEntity(registrationRequestDto));

        return ResponseEntity.ok(
            userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }
    

}
