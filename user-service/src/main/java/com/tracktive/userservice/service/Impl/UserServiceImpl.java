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
        return userRepository.selectUserById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserDTO lockUserById(String id) {
        try{
            return userRepository.lockUserById(id)
                    .orElseThrow(()->new UserNotFoundException("User not found with id: " + id));
        }catch (CannotAcquireLockException e){
            logger.warn("Lock acquisition failed for user id: {}", id);
            throw new LockAcquisitionException("Failed to acquire lock for user with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public boolean addUser(UserDTO userDTO) {
        return userRepository.addUser(userDTO);
    }

    @Override
    @Transactional
    public boolean updateUser(UserDTO userDTO) {
        boolean updated = userRepository.updateUser(userDTO);
        if (!updated) {
            logger.info("Failed to update: User not found with id: {}", userDTO.getId());
            throw new UserNotFoundException("Failed to update: User not found with id: " + userDTO.getId());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUserById(String id) {
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: User not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: User not found with id: " + id);
        }
        return true;
    }
}
