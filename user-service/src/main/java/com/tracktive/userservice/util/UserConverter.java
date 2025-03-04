package com.tracktive.userservice.util;

import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.entity.User;
import java.util.Objects;

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
        return user;
    }
}
