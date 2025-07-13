package com.music.love.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.music.love.app.dto.AuthenticationRequestDto;
import com.music.love.app.dto.AuthenticationResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponseDto authenticate(
        final AuthenticationRequestDto request
    ) {
        try {
            final var authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.username(), request.password());
    
            authenticationManager.authenticate(authToken);
            final var token = jwtService.generateToken(request.username());
            return new AuthenticationResponseDto(token);
            
        } catch (Exception e) {
            throw e;
        }


    }


}
