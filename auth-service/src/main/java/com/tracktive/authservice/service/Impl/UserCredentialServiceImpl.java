package com.tracktive.authservice.service.Impl;

import com.tracktive.authservice.exception.LockAcquisitionException;
import com.tracktive.authservice.exception.UserCredentialNotFoundException;
import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.DTO.UserCredentialRequestDTO;
import com.tracktive.authservice.repository.UserCredentialsRepository;
import com.tracktive.authservice.service.UserCredentialService;
import com.tracktive.authservice.util.UserCredentialConverter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* Description: User Credential Service Implementation
* @author William Theo
* @date 23/4/2025
*/
@Service
public class UserCredentialServiceImpl implements UserCredentialService {

    private final Validator validator;

    private final UserCredentialsRepository userCredentialsRepository;

    private static final Logger log = LoggerFactory.getLogger(UserCredentialServiceImpl.class);

    @Autowired
    public UserCredentialServiceImpl(Validator validator, UserCredentialsRepository userCredentialsRepository) {
        this.validator = validator;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public List<UserCredentialDTO> selectAll() {
        log.info("UserCredentialService: selecting all user credential.");
        return userCredentialsRepository.selectAll();
    }

    @Override
    public UserCredentialDTO selectById(String id) {
        validateId(id);
        return userCredentialsRepository.selectById(id)
                .orElseThrow(()->{
                    log.warn("UserCredentialService: User Credential not found with user id: {}", id);
                    return new UserCredentialNotFoundException("User Credential not found with user id:" + id);
                });
    }

    @Override
    public UserCredentialDTO lockById(String id) {
        validateId(id);
        try {
            return userCredentialsRepository.lockById(id)
                    .orElseThrow(() -> {
                        log.warn("Failed to lock user credential, user credential not found with user id: {}", id);
                        return new UserCredentialNotFoundException("User Credential not found with user id:" + id);
                    });
        } catch (PersistenceException e) {
            log.error("Persistence error occurred during lock acquisition for user credential with user id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for user credential with user id: " + id, e);
        } catch (Exception e) {
            log.error("Unexpected error during user credential lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    public UserCredentialDTO addUserCredential(UserCredentialRequestDTO userCredentialRequestDTO) {
        UserCredentialDTO userCredentialDTO = UserCredentialConverter.toDTO(userCredentialRequestDTO);
        validateUserCredentialDTO(userCredentialDTO);
        boolean result = userCredentialsRepository.addUserCredential(userCredentialDTO);
        if (!result) {
            log.error("Failed to add user credential with id: {}", userCredentialDTO.getUserId());
            throw new RuntimeException("Failed to add user credential with id: " + userCredentialDTO.getUserId());
        }
        log.info("User Credential [{}] add successfully", userCredentialDTO.getUserId());

        return userCredentialsRepository.selectById(userCredentialDTO.getUserId())
                .orElseThrow(()-> new UserCredentialNotFoundException("Failed to fetch user credential after insertion"));
    }

    @Override
    public UserCredentialDTO updateUserCredential(UserCredentialDTO userCredentialDTO) {
        validateUserCredentialDTO(userCredentialDTO);
        boolean result = userCredentialsRepository.updateUserCredential(userCredentialDTO);
        if (!result) {
            log.error("Failed to update user credential with id: {}", userCredentialDTO.getUserId());
            throw new RuntimeException("Failed to update user credential with id: " + userCredentialDTO.getUserId());
        }
        log.info("User Credential [{}] updated successfully", userCredentialDTO.getUserId());

        return userCredentialsRepository.selectById(userCredentialDTO.getUserId())
                .orElseThrow(()-> new UserCredentialNotFoundException("Failed to fetch user credential after update"));
    }

    @Override
    public void deleteById(String id) {
        validateId(id);
        boolean result = userCredentialsRepository.deleteById(id);
        if (!result) {
            log.error("Failed to delete user credential with id: {}", id);
            throw new UserCredentialNotFoundException("Failed to delete retailer with id: " + id);
        }
        log.info("Retailer [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateUserCredentialDTO(UserCredentialDTO userCredentialDTO) {
        Set<ConstraintViolation<UserCredentialDTO>> violations = validator.validate(userCredentialDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for userCredentialDTO", violations);
        }
    }
}
