package com.tracktive.authservice.service;

import com.tracktive.authservice.model.DTO.UserCredentialManagementRequestDTO;
import com.tracktive.authservice.model.DTO.UserCredentialManagementResponseDTO;

/**
* Description: User Credential Management Service Interface
* @author William Theo
* @date 28/4/2025
*/
public interface UserCredentialManagementService {
    UserCredentialManagementResponseDTO updatePassword(UserCredentialManagementRequestDTO userCredentialManagementRequestDTO);
}
