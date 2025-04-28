package com.tracktive.authservice.controller;

import com.tracktive.authservice.model.DTO.UserCredentialManagementRequestDTO;
import com.tracktive.authservice.model.DTO.UserCredentialManagementResponseDTO;
import com.tracktive.authservice.service.UserCredentialManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: User Credential Management Rest API End Point (Change user password)
* @author William Theo
* @date 28/4/2025
*/
@RestController
@RequestMapping("/user-credentials")
public class UserCredentialManagementController {

    private final UserCredentialManagementService userCredentialManagementService;

    @Autowired
    public UserCredentialManagementController(UserCredentialManagementService userCredentialManagementService) {
        this.userCredentialManagementService = userCredentialManagementService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserCredentialManagementResponseDTO> changePassword(@Valid @RequestBody UserCredentialManagementRequestDTO userCredentialManagementRequestDTO){
        UserCredentialManagementResponseDTO result = userCredentialManagementService.updatePassword(userCredentialManagementRequestDTO);
        return ResponseEntity.ok(result);
    }
}
