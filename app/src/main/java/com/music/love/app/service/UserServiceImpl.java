package com.music.love.app.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
    public Optional<UserDTO> getUserById(Long id){
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::convertToDTO);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    public UserDTO saveUser(UserDTO userDTO){
        MyUser user = convertToEntity(userDTO);
        MyUser savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO){
        MyUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setAddress(userDTO.address());
        user.setCity(userDTO.city());
        user.setState(userDTO.state());
        user.setCountry(userDTO.country());
        user.setPinCode(userDTO.pinCode());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setRegister_as(userDTO.register_as());
        user.setProfilePicture(userDTO.profilePicture());
        user.setGovernmentPictureId(userDTO.governmentPictureId());
        user.setUpdatedAt(Instant.now());
        MyUser updatedUser = userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(MyUser user){
        return new UserDTO(user.getId(),user.getUsername(),user.getEmail(), 
            user.getRegister_as(), user.getPhoneNumber(), user.getCountry(), 
            user.getCity(), user.getAddress(), user.getState(), user.getPinCode(),
            user.getProfilePicture(), user.getGovernmentPictureId());
    }

    private MyUser convertToEntity(UserDTO userDTO){
        MyUser user = new MyUser();
        user.setId(userDTO.id());
        user.setEmail(userDTO.email());
        user.setUsername(userDTO.username());
        user.setAddress(userDTO.address());
        user.setCity(userDTO.city());
        user.setState(userDTO.state());
        user.setCountry(userDTO.country());
        user.setPinCode(userDTO.pinCode());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setRegister_as(userDTO.register_as());
        user.setProfilePicture(userDTO.profilePicture());
        user.setGovernmentPictureId(userDTO.governmentPictureId());

        return user;
    }
}
