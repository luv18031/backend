package com.music.love.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.music.love.app.dto.AuthenticationRequestDto;
import com.music.love.app.dto.RegistrationRequestDto;
import com.music.love.app.entity.MyUser;
import com.music.love.app.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MyUser signup(RegistrationRequestDto input) {
        MyUser user = new MyUser();
                user.setUsername(input.username());
                user.setEmail(input.email());
                user.setPassword(passwordEncoder.encode(input.password()));

        return userRepository.save(user);
    }

    public MyUser authenticate(AuthenticationRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );

        return userRepository.findByUsername(input.username()).orElse(null);
    }
}