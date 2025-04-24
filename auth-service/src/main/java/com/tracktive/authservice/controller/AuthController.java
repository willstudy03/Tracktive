package com.tracktive.authservice.controller;

import com.tracktive.authservice.model.DTO.LoginRequestDTO;
import com.tracktive.authservice.model.DTO.LoginResponseDTO;
import com.tracktive.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: Authentication Rest API End Point
* @author William Theo
* @date 24/4/2025
*/
@RestController
@RequestMapping("authentication")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            LoginResponseDTO responseDTO = authService.authenticate(loginRequestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
