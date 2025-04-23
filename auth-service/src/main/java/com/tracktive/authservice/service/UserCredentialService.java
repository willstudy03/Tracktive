package com.tracktive.authservice.service;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.DTO.UserCredentialRequestDTO;

import java.util.List;

/**
* Description: User Credential Service Interface
* @author William Theo
* @date 23/4/2025
*/
public interface UserCredentialService {
    // Select operations
    List<UserCredentialDTO> selectAll();
    UserCredentialDTO selectById(String id);
    // Lock Operation
    UserCredentialDTO lockById(String id);
    // Insert operation
    UserCredentialDTO addUserCredential(UserCredentialRequestDTO userCredentialRequestDTO);
    // Update operation
    UserCredentialDTO updateUserCredential(UserCredentialDTO userCredentialDTO);
    // Delete operation
    void deleteById(String id);
}
