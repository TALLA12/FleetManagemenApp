package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.User;
import com.ardotech.VoitureManagement.Enum.UserStatus;
import com.ardotech.VoitureManagement.Repository.UserRepository;
import com.ardotech.VoitureManagement.configuration.JwtUtils;
import com.ardotech.VoitureManagement.dto.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public User registerUser(User user) {
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        user.setStatus(UserStatus.INACTIVE);

        return userRepository.save(user);
    }

    public Map<String, Object> login(LoginRequest loginRequest) throws AuthenticationException {
        try {
            System.out.println("Attempting to authenticate user: " + loginRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getMotDePasse())
            );

            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful for user: " + loginRequest.getUsername());

                User user = userRepository.findByUsername(loginRequest.getUsername());
                if (user != null && user.getStatus() == UserStatus.ACTIVE) {
                    String token = jwtUtils.generateToken(loginRequest.getUsername());
                    Map<String, Object> authData = new HashMap<>();
                    authData.put("token", token);
                    authData.put("type", "Bearer");
                    return authData;
                } else {
                    throw new AuthenticationException("User account is not active") {};
                }
            } else {
                throw new AuthenticationException("Invalid username or password") {};
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed for user " + loginRequest.getUsername() + ": " + e.getMessage());
            throw e;
        }
    }


}
