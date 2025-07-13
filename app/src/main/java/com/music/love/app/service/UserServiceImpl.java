package com.music.love.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.music.love.app.dto.UserDTO;
import com.music.love.app.entity.MyUser;
import com.music.love.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this:: convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id){
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO){
        MyUser user = convertToEntity(userDTO);
        MyUser savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UUID id,UserDTO userDTO){
        MyUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        MyUser updatedUser = userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(MyUser user){
        return new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getPassword());
    }

    private MyUser convertToEntity(UserDTO userDTO){
        MyUser user = new MyUser();
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setUsername(userDTO.username());

        return user;
    }
}
