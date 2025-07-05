package com.music.app.backend.mapper;

import org.springframework.stereotype.Component;

import com.music.app.backend.dto.RegistrationRequestDto;
import com.music.app.backend.dto.RegistrationResponseDto;
import com.music.app.backend.entity.MyUser;

// UserRegistrationMapper.java
@Component
public class UserRegistrationMapper {

    public MyUser toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new MyUser();

        user.setEmail(registrationRequestDto.getEmail());
        user.setUsername(registrationRequestDto.getUsername());
        user.setPassword(registrationRequestDto.getPassword());

        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(
      final MyUser user) {
        
        return new RegistrationResponseDto(
          user.getEmail(), user.getUsername());
    }

}