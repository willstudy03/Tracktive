package com.tracktive.userservice.repository;

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
    List<User> selectAllUsers();
    // Find user by ID
    Optional<User> selectUserById(String id);
    // Lock a user by ID
    Optional<User> lockUserById(String id);
    // Insert a new user
    boolean addUser(User user);
    // Update user info
    boolean update(User user);
    // Delete user by ID
    boolean deleteById(String id);
}

