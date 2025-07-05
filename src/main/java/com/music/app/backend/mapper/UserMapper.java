package com.music.app.backend.mapper;

import org.springframework.stereotype.Component;

import com.music.app.backend.dto.UserProfileDto;
import com.music.app.backend.entity.MyUser;

// UserMapper.java
@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final MyUser user) {
        return new UserProfileDto(user.getEmail(), user.getUsername());
    }
}
