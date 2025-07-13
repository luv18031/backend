package com.music.love.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.music.love.app.dto.UserDTO;

public interface UserService {

    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(UUID id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UUID id, UserDTO userDTO);
    void deleteUser(UUID id);
    
    
} 