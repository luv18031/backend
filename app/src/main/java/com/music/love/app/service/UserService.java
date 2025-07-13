package com.music.love.app.service;

import java.util.List;
import java.util.Optional;

import com.music.love.app.dto.UserDTO;

public interface UserService {

    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    
    
} 