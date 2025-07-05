package com.music.app.backend.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationResponseDto implements Serializable {
        private String username;
        private String email;
}
