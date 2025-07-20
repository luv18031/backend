package com.music.love.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
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


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
        @RequestBody final AuthenticationRequestDto authenticationRequestDto
    ) {
        MyUser authenticatedUser = authenticationService.authenticate(authenticationRequestDto);
        System.out.println(SecurityContextHolder.getContext());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()==false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String jwtToken = jwtService.generateToken(authenticatedUser);


        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        authResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(authResponse);
    }
}
