package com.michelegrieco.taskly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelegrieco.taskly.dto.UserDTO;
import com.michelegrieco.taskly.dto.UserMapper;
import com.michelegrieco.taskly.model.User;
import com.michelegrieco.taskly.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User saved = userService.registerUser(user);
        UserDTO responseDTO = UserMapper.toDTO(saved);
        responseDTO.setPassword(null); // Skip password
        return ResponseEntity.ok(userDTO);
    }
}