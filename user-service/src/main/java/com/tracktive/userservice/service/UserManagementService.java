package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;

import java.util.List;

/**
* Description: User Management Service Interface
* @author William Theo
* @date 22/4/2025
*/
public interface UserManagementService {
    UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO);
}
