package com.music.app.backend.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;



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

    @GetMapping("/csrf-token")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<CsrfToken> csrf(HttpServletRequest request) {
        return ResponseEntity.ok().body((CsrfToken) request.getAttribute(CsrfToken.class.getName()));
        // return ResponseEntity.status(HttpStatus.OK)
        // .body("Your age is " + 2022);
    }
    

    /**
     * Maps the admin home page.
     * 
     * @return the view name for the admin home page
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String entity, Principal principal) {
        
        return ResponseEntity.ok().body(principal.getName());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hello")
    public ResponseEntity<String> userHello(Principal principal) {
        return ResponseEntity.ok().body(principal.getName());
    }
}
