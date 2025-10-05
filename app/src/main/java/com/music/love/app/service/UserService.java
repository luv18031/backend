package com.music.love.app.service;

import java.util.List;
import java.util.Optional;

import com.music.love.app.dto.UserDTO;
import com.music.love.app.entity.MyUser;

public interface UserService {

    List<MyUser> getAllUsers();
    Optional<MyUser> getUserById(Long id);
    MyUser saveUser(MyUser user);
    MyUser updateUser(Long id, MyUser user);
    void deleteUser(Long id);
    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
} 