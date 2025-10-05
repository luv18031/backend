package com.music.love.app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.UserDTO;
import com.music.love.app.entity.MyUser;
import com.music.love.app.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/profile")
public class UserProfileController {
    
    // This class is currently empty, but you can implement methods for user profile management here.
    // For example, methods to update user profiles, get user profile details, etc.
    
    // Example method to get user profile by ID
    // @GetMapping("/{id}/profile")
    // public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Long id) {
    //     Optional<UserProfileDTO> profile = userProfileService.getUserProfileById(id);
    //     return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }
    private final UserService userService;

    UserProfileController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<UserDTO> getUserProfile(@RequestParam final String username) {
        // Placeholder for user profile retrieval logic
        System.out.println("authenticated user: " + SecurityContextHolder.getContext().getAuthentication().getName());
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<MyUser> userResp = userService.findByUsername(username);
        if(userResp == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok().body(this.convertToDTO(userResp.get()));
    }

    @PatchMapping
    public ResponseEntity<UserDTO> saveUserProfile(@RequestParam final String username, @RequestBody final UserDTO userDTO ) {
        
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<MyUser> userResp = userService.findByUsername(username);
        if(userResp == null) {
            return ResponseEntity.notFound().build();
        }

        MyUser existingUser = userResp.get();
        if(!existingUser.getUsername().equals(userDTO.username()) && userService.existsByUsername(userDTO.username())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        if(!existingUser.getEmail().equals(userDTO.email()) && userService.existsByEmail(userDTO.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        if(!existingUser.getAddress().equals(userDTO.address())) {
            existingUser.setAddress(userDTO.address());
        }
        if(!existingUser.getCity().equals(userDTO.city())) {
            existingUser.setCity(userDTO.city());
        }
        if(!existingUser.getState().equals(userDTO.state())) {
            existingUser.setState(userDTO.state());
        }
        if(!existingUser.getCountry().equals(userDTO.country())) {
            existingUser.setCountry(userDTO.country());
        }
        if(!existingUser.getPinCode().equals(userDTO.pinCode())) {
            existingUser.setPinCode(userDTO.pinCode());
        }
        if(!existingUser.getPhoneNumber().equals(userDTO.phoneNumber())) {
            existingUser.setPhoneNumber(userDTO.phoneNumber());
        }
        if(!existingUser.getRegister_as().equals(userDTO.register_as())) {
            existingUser.setRegister_as(userDTO.register_as());
        }
        existingUser.setProfilePicture(userDTO.profilePicture());
        existingUser.setGovernmentPictureId(userDTO.governmentPictureId());
        
        return ResponseEntity.ok().body(this.convertToDTO(userService.updateUser(userResp.get().getId(), existingUser)));
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
