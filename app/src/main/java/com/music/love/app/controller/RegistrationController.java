package com.music.love.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.RegistrationRequestDto;
import com.music.love.app.dto.RegistrationResponseDto;
import com.music.love.app.mapper.UserRegistrationMapper;
import com.music.love.app.service.UserRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {
    
    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper userRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(@Valid @RequestBody final RegistrationRequestDto registrationRequestDto) {

        final var registeredUser = userRegistrationService.registerUser(userRegistrationMapper.toEntity(registrationRequestDto));

        return ResponseEntity.ok(
            userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }
    

}
