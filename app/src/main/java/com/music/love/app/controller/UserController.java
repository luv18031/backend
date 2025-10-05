package com.music.love.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.UserDTO;
import com.music.love.app.entity.MyUser;
import com.music.love.app.service.UserService;




@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        // @RequestHeader HttpHeaders headers
        // HttpHeaders headers = new HttpHeaders();

        // headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return ResponseEntity.ok()
            // .headers(headers)
            .body(
                userService.getAllUsers().stream()
                    .map(this::convertToDTO)
                    .toList()
            );
        // return ResponseEntity.ok().headers(headers).body(userService.getAllUsers() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id).map(this::convertToDTO);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return this.convertToDTO(userService.saveUser(this.convertToEntity(userDTO)));    
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            MyUser updatedUser = userService.updateUser(id, this.convertToEntity(userDTO));
            return ResponseEntity.ok().body(this.convertToDTO(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
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
