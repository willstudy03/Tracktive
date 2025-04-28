package com.tracktive.authservice.service.Impl;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.DTO.UserCredentialManagementRequestDTO;
import com.tracktive.authservice.model.DTO.UserCredentialManagementResponseDTO;
import com.tracktive.authservice.service.UserCredentialManagementService;
import com.tracktive.authservice.service.UserCredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Description: User Credential Management Service Implementation
* @author William Theo
* @date 28/4/2025
*/
@Service
public class UserCredentialManagementServiceImpl implements UserCredentialManagementService {

    private final UserCredentialService userCredentialService;

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserCredentialManagementServiceImpl.class);

    @Autowired
    public UserCredentialManagementServiceImpl(UserCredentialService userCredentialService, PasswordEncoder passwordEncoder) {
        this.userCredentialService = userCredentialService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserCredentialManagementResponseDTO updatePassword(UserCredentialManagementRequestDTO userCredentialManagementRequestDTO) {

        log.info("UserCredentialManagementService: Received password update request from user {}", userCredentialManagementRequestDTO.getUserId());

        // Step 1: Ensure user is existed, else exception will be thrown
        UserCredentialDTO userCredentialDTO = userCredentialService.lockById(userCredentialManagementRequestDTO.getUserId());

        // Step 2: encode the password
        userCredentialDTO.setPasswordHash(passwordEncoder.encode(userCredentialManagementRequestDTO.getNewPassword()));

        // Step 3: update the password into the db
        UserCredentialDTO updatedCredential = userCredentialService.updateUserCredential(userCredentialDTO);

        log.info("UserCredentialManagementService: Updated password for user {}", userCredentialManagementRequestDTO.getUserId());

        return new UserCredentialManagementResponseDTO(updatedCredential.getUserId(), true);
    }
}
