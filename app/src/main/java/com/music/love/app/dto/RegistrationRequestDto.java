package com.music.love.app.dto;

public record RegistrationRequestDto(
        String username,
        String email,
        String password
) {
}