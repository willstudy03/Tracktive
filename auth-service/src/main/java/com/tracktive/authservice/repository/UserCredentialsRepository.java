package com.tracktive.authservice.repository;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.entity.UserCredential;

import java.util.List;
import java.util.Optional;

/**
* Description: User Credentials Repository Interface
* @author William Theo
* @date 23/4/2025
*/
public interface UserCredentialsRepository {
    // Select operations
    List<UserCredentialDTO> selectAll();
    Optional<UserCredentialDTO> selectById(String id);
    Optional<UserCredentialDTO> selectByEmail(String email);
    // Lock Operation
    Optional<UserCredentialDTO> lockById(String id);
    // Insert operation
    boolean addUserCredential(UserCredentialDTO userCredentialDTO);
    // Update operation
    boolean updateUserCredential(UserCredentialDTO userCredentialDTO);
    // Delete operation
    boolean deleteById(String id);
}
