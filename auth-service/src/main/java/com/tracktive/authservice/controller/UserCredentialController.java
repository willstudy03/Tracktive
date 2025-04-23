package com.tracktive.authservice.controller;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.DTO.UserCredentialRequestDTO;
import com.tracktive.authservice.service.UserCredentialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
public class UserCredentialController {

    private final UserCredentialService userCredentialService;

    @Autowired
    public UserCredentialController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @PostMapping
    public ResponseEntity<UserCredentialDTO> newUser(@Valid @RequestBody UserCredentialRequestDTO userCredentialRequestDTO){
        UserCredentialDTO userCredentialDTO = userCredentialService.addUserCredential(userCredentialRequestDTO);
        return ResponseEntity.ok(userCredentialDTO);
    }
}
