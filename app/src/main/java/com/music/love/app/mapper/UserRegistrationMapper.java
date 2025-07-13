package com.music.love.app.mapper;

import org.springframework.stereotype.Component;

import com.music.love.app.dto.RegistrationRequestDto;
import com.music.love.app.dto.RegistrationResponseDto;
import com.music.love.app.entity.MyUser;

@Component
public class UserRegistrationMapper {
    public MyUser toEntity(RegistrationRequestDto registrationRequestDto){
        
        final var user = new MyUser();
        
        user.setEmail(registrationRequestDto.email());
        user.setUsername(registrationRequestDto.username());
        user.setPassword(registrationRequestDto.password());

        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(final MyUser user){

        return new RegistrationResponseDto(user.getUsername(), user.getEmail());
    }
}
