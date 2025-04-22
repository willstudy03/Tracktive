package com.tracktive.userservice.controller;

import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.DTO.UserRequestDTO;
import com.tracktive.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description:
* @author William Theo
* @date 22/4/2025
*/
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.selectAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> newUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserDTO userDTO = userService.addUser(userRequestDTO);
        return ResponseEntity.ok(userDTO);
    }
}
