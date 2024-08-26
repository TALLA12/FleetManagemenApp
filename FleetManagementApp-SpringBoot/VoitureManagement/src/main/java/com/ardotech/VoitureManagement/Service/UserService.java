package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.User;
import com.ardotech.VoitureManagement.Enum.UserStatus;
import com.ardotech.VoitureManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("No authenticated user found");
        }
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUserStatus(Long id, UserStatus status) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(status);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
