package com.music.app.backend.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ContentController {

    /**
     * Maps the home page.
     * 
     * @return the view name for the home page
     */
    // @GetMapping("/")
    // public ResponseEntity<Void> home() {
    //     return ResponseEntity.ok().build();
    // }

    /**
     * Maps the home alias page.
     * 
     * @return the view name for the home page
     */
    // @GetMapping("/home")
    // public ResponseEntity<Void> homeAlias() {
    //     return ResponseEntity.ok().build();
    // }

    /**
     * Maps the login page.
     * 
     * @return the view name for the login page
     */
    // @GetMapping("/login")
    // public ResponseEntity<Void> login() {
    //     return ResponseEntity.ok().build();
    // }

    // @GetMapping("/api/auth/register")
    // public ResponseEntity<Void> register() {
    //     // return new ResponseEntity<>(HttpStatus.OK);
    //     return ResponseEntity.ok().build();
    // }


    /**
     * Maps the admin home page.
     * 
     * @return the view name for the admin home page
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String entity, Principal principal) {
        
        return ResponseEntity.ok().body(principal.getName());
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> userHello(Principal principal) {
        return ResponseEntity.ok().body(principal.getName());
    }
}
