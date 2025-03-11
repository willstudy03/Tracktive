package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.UserDTO;
import java.util.List;

/**
* Description: User CRUD Service Interface
* @author William Theo
* @date 5/3/2025
*/
public interface UserService {
    List<UserDTO> selectAllUsers();
    UserDTO selectUserById(String id);
    UserDTO lockUserById(String id);
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUserById(String id);
}
