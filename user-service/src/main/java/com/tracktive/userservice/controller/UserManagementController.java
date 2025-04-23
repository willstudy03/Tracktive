package com.tracktive.userservice.controller;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: User Management Admin REST API End Point
* @author William Theo
* @date 23/4/2025
*/
@RestController
@RequestMapping("users/management")
public class UserManagementController {

    private final UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> newUser(@Valid @RequestBody UserCreationRequestDTO userCreationRequestDTO){
        UserDTO userDTO = userManagementService.crateUser(userCreationRequestDTO);
        return ResponseEntity.ok(userDTO);
    }
}
