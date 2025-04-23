package com.tracktive.authservice.repository.Impl;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.repository.UserCredentialsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class UserCredentialsRepositoryImplTest {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    private static final Logger log = LoggerFactory.getLogger(UserCredentialsRepositoryImplTest.class);

    @Test
    void selectAll() {
        List<UserCredentialDTO> userCredentialDTOS = userCredentialsRepository.selectAll();
        log.info("Users:{}", userCredentialDTOS);
        assertEquals(userCredentialDTOS.isEmpty(),false);
    }

    @Test
    void selectById() {
        String id = "d461d3ea-3b02-47d3-978e-f7d5b4c97f0a";
        Optional<UserCredentialDTO> userCredentialDTO = userCredentialsRepository.selectById(id);
        UserCredentialDTO result = userCredentialDTO.get();
        assertEquals(id, result.getUserId());
    }

    @Test
    void lockById() {
        String id = "d461d3ea-3b02-47d3-978e-f7d5b4c97f0a";
        Optional<UserCredentialDTO> userCredentialDTO = userCredentialsRepository.lockById(id);
        UserCredentialDTO result = userCredentialDTO.get();
        assertEquals(id, result.getUserId());
    }

    @Test
    void addUserCredential() {
        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();
        userCredentialDTO.setUserId(UUID.randomUUID().toString());
        userCredentialDTO.setEmail("testing@gmail.com");
        userCredentialDTO.setPasswordHash("tracktive");
        userCredentialDTO.setMustResetPassword(false);

        boolean isAdded = userCredentialsRepository.addUserCredential(userCredentialDTO);
        assertTrue(isAdded);
    }

    @Test
    void updateUserCredential() {
        String id = "d461d3ea-3b02-47d3-978e-f7d5b4c97f0a";
        Optional<UserCredentialDTO> userCredentialDTO = userCredentialsRepository.lockById(id);
        UserCredentialDTO result = userCredentialDTO.get();
        assertEquals(id, result.getUserId());
        result.setMustResetPassword(true);
        boolean isUpdated = userCredentialsRepository.updateUserCredential(result);
        assertTrue(isUpdated);
        assertEquals(true, userCredentialsRepository.selectById(id).get().isMustResetPassword());
    }

    @Test
    void deleteById() {
        String id = "d461d3ea-3b02-47d3-978e-f7d5b4c97f0a";
        boolean isDeleted = userCredentialsRepository.deleteById(id);
        assertTrue(isDeleted);
    }
}