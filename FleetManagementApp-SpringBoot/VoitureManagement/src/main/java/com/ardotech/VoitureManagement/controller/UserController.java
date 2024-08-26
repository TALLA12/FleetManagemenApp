package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.User;
import com.ardotech.VoitureManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ardotech.VoitureManagement.Enum.UserStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List<User> userList = userService.allUsers();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> updateUserStatus(@PathVariable Long id, @RequestBody Map<String, String> status) {
        try {
            UserStatus userStatus = UserStatus.valueOf(status.get("status"));
            userService.updateUserStatus(id, userStatus);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User status updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid status value"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/current")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }
}
