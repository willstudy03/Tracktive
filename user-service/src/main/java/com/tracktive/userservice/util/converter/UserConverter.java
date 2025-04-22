package com.tracktive.userservice.util.converter;

import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.DTO.UserRequestDTO;
import com.tracktive.userservice.model.entity.User;
import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert user model
* @author William Theo
* @date 3/3/2025
* @param
* @return
*/
public class UserConverter {

    // Private constructor to prevent instantiation
    private UserConverter(){}

    public static UserDTO toDTO(User user){
        if (Objects.isNull(user)){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUserRole(user.getUserRole());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        if (Objects.isNull(userDTO)){
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUserRole(userDTO.getUserRole());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }

    public static UserDTO toDTO(UserRequestDTO userRequestDTO){
        if (Objects.isNull(userRequestDTO)){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setName(userRequestDTO.getName());
        userDTO.setEmail(userRequestDTO.getEmail());
        userDTO.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userDTO.setUserRole(userRequestDTO.getUserRole());
        return userDTO;
    }
}
