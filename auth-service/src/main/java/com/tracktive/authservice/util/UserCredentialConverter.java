package com.tracktive.authservice.util;

import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.DTO.UserCredentialRequestDTO;
import com.tracktive.authservice.model.entity.UserCredential;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
* Description: Util for convert user credential model
* @author William Theo
* @date 23/4/2025
*/
public class UserCredentialConverter {

    // Private constructor to prevent instantiation
    private UserCredentialConverter(){}
    
    public static UserCredentialDTO toDTO(UserCredential userCredential){
        if (Objects.isNull(userCredential)){
            return null;
        }
        UserCredentialDTO dto = new UserCredentialDTO();
        BeanUtils.copyProperties(userCredential, dto);
        return dto;
    }

    public static UserCredential toEntity(UserCredentialDTO userCredentialDTO){
        if (Objects.isNull(userCredentialDTO)){
            return null;
        }
        UserCredential userCredential = new UserCredential();
        BeanUtils.copyProperties(userCredentialDTO, userCredential);
        return userCredential;
    }

    public static UserCredentialDTO toDTO(UserCredentialRequestDTO userCredentialRequestDTO){
        if (Objects.isNull(userCredentialRequestDTO)){
            return null;
        }
        UserCredentialDTO dto = new UserCredentialDTO();
        dto.setUserId(userCredentialRequestDTO.getUserId());
        dto.setEmail(userCredentialRequestDTO.getEmail());
        dto.setPasswordHash("tracktive");
        dto.setMustResetPassword(true);
        return dto;
    }
}
