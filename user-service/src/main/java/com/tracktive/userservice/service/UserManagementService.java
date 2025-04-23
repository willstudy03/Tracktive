package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.DTO.UserManagementRequestDTO;
import com.tracktive.userservice.model.DTO.UserManagementResponseDTO;

import java.util.List;

/**
* Description: User Management Service Interface
* @author William Theo
* @date 22/4/2025
*/
public interface UserManagementService {
    List<UserDTO> selectAllUsers();
    UserManagementResponseDTO selectUserById(String id);
    UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO);
    UserManagementResponseDTO updateUser(UserManagementRequestDTO userManagementRequestDTO);
}
