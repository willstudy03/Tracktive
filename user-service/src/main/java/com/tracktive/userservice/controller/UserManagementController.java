package com.tracktive.userservice.controller;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.DTO.UserManagementRequestDTO;
import com.tracktive.userservice.model.DTO.UserManagementResponseDTO;
import com.tracktive.userservice.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: User Management Admin REST API End Point
* @author William Theo
* @date 23/4/2025
*/
@RestController
@RequestMapping("/users-management")
public class UserManagementController {

    private final UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userManagementService.selectAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserManagementResponseDTO> selectUserById(@PathVariable String userId){
        UserManagementResponseDTO user = userManagementService.selectUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> newUser(@Valid @RequestBody UserCreationRequestDTO userCreationRequestDTO){
        UserDTO userDTO = userManagementService.createUser(userCreationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/users")
    public ResponseEntity<UserManagementResponseDTO> updateUser(@Valid @RequestBody UserManagementRequestDTO userManagementRequestDTO) {
        UserManagementResponseDTO user = userManagementService.updateUser(userManagementRequestDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        UserDTO deletedUser = userManagementService.deleteUserById(userId);
        return ResponseEntity.ok("User with ID " + deletedUser.getId() + " deleted successfully.");
    }
}
