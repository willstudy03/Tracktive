package com.tracktive.userservice.repository;

import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.entity.User;
import java.util.List;
import java.util.Optional;

/**
* Description: User Repository Interface
* @author William Theo
* @date 1/3/2025
*/
public interface UserRepository {
    // Get all users
    List<UserDTO> selectAllUsers();
    // Find user by ID
    Optional<UserDTO> selectUserById(String id);
    // Lock a user by ID
    Optional<UserDTO> lockUserById(String id);
    // Insert a new user
    boolean addUser(UserDTO userDTO);
    // Update user info
    boolean updateUser(UserDTO userDTO);
    // Delete user by ID
    boolean deleteById(String id);
}

