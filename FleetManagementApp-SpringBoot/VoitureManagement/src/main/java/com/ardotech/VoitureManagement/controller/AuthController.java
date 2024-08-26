package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.User;
import com.ardotech.VoitureManagement.Service.AuthService;
import com.ardotech.VoitureManagement.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.Collections;


import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            User registeredUser = authService.registerUser(user);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body("{\"message\": \"User registered successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, "application/json").body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Map<String, Object> authData = authService.login(loginRequest);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(authData);
        } catch (AuthenticationException e) {
            String errorMessage = "Invalid username or password";
            if (e.getMessage().equals("User account is not active")) {
                errorMessage = "User account is not active";
            }
            System.out.println("Authentication failed for user " + loginRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", errorMessage));
        }
    }

}
