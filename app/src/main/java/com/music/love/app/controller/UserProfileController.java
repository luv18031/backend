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
        Optional<UserDTO> userResp = userService.findByUsername(username);
        if(userResp == null) {
            return ResponseEntity.notFound().build();
        }
        UserDTO user = userResp.get();
        return ResponseEntity.ok(user);
    }

    @PatchMapping
    public ResponseEntity<UserDTO> saveUserProfile(@RequestBody final UserDTO userProfileRequestDto) {
        
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<UserDTO> userResp = userService.findByUsername(userProfileRequestDto.username());
        if(userResp == null) {
            return ResponseEntity.notFound().build();
        }
        UserDTO user = userResp.get();

        userService.saveUser(user);
        
        return ResponseEntity.ok(user);
    }
    
}
