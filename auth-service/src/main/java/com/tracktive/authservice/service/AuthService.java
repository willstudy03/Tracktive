package com.tracktive.authservice.service;

import com.tracktive.authservice.model.DTO.LoginRequestDTO;
import com.tracktive.authservice.model.DTO.LoginResponseDTO;

/**
* Description: Auth Service Interface
* @author William Theo
* @date 24/4/2025
*/
public interface AuthService {
    LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO);
}
