package com.music.love.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.AuthenticationRequestDto;
import com.music.love.app.dto.AuthenticationResponseDto;
import com.music.love.app.entity.MyUser;
import com.music.love.app.service.AuthenticationService;
import com.music.love.app.service.JwtService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
        @RequestBody final AuthenticationRequestDto authenticationRequestDto
    ) {
        MyUser authenticatedUser = authenticationService.authenticate(authenticationRequestDto);
        
        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationResponseDto authResonse = new AuthenticationResponseDto();
        authResonse.setToken(jwtToken);
        authResonse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(authResonse);
    }
}
