package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.repository.UserRepository;
import com.tracktive.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* Description: User CRUD Service Implementation
* @author William Theo
* @date 5/3/2025
*/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> selectAllUsers() {
        return userRepository.selectAllUsers();
    }

    @Override
    public UserDTO selectUserById(String id) {
        validateId(id);
        return userRepository.selectUserById(id)
                .orElseThrow(()->{
                    logger.warn("User not found with id: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public UserDTO lockUserById(String id) {
        validateId(id);
        try {
            return userRepository.lockUserById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock user, user not found with id: {}", id);
                        return new UserNotFoundException("User not found with id: " + id);
                    });
        } catch (CannotAcquireLockException e) {
            logger.error("Lock acquisition failed for user id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for user with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during user lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        validateUserDTO(userDTO);
        boolean result = userRepository.addUser(userDTO);
        if (!result) {
            logger.error("Failed to add user with id: {}", userDTO.getId());
            throw new RuntimeException("Failed to add user with id: " + userDTO.getId());
        }
        logger.info("User [{}] added successfully", userDTO.getId());
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        validateUserDTO(userDTO);
        boolean result = userRepository.updateUser(userDTO);
        if (!result) {
            logger.error("Failed to update user with id: {}", userDTO.getId());
            throw new UserNotFoundException("Failed to update user with id: " + userDTO.getId());
        }
        logger.info("User [{}] updated successfully", userDTO.getId());
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        validateId(id);
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: User not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: User not found with id: " + id);
        }
        logger.warn("User [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateUserDTO(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }
        //@TODO: Validation
    }
}
