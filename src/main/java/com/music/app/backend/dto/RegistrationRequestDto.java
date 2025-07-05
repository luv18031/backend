package com.music.app.backend.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// RegistrationRequestDto.java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequestDto implements Serializable{
        private String username;
        private String email;
        private String password;
}
