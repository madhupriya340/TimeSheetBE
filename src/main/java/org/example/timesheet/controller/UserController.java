package org.example.timesheet.controller;

import jakarta.websocket.server.PathParam;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.UserInfoRequest;
import org.example.timesheet.model.UserInfo;
import org.example.timesheet.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserInfoRequest user) {
        ApiResponse res= userService.createUser(user);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping("/type")
    public ResponseEntity<?> getUsersByType(@RequestParam String type) {
        System.out.println("controller entered"+type);
        ApiResponse res= userService.getUsersByType(type);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        ApiResponse res=userService.getAllUsers();
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathParam("userId") UUID userID, @RequestBody UserInfo user) {
        ApiResponse res=userService.updateUserInfo(userID,user);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
