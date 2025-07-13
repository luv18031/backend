package com.music.love.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDto {

    private String token;

    private long expiresIn;

}