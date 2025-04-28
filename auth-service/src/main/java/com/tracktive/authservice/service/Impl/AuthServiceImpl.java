package com.tracktive.authservice.service.Impl;

import com.tracktive.authservice.exception.UserCredentialNotFoundException;
import com.tracktive.authservice.model.DTO.LoginRequestDTO;
import com.tracktive.authservice.model.DTO.LoginResponseDTO;
import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.service.AuthService;
import com.tracktive.authservice.service.UserCredentialService;
import com.tracktive.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* Description: Authentication Service Implementation
* @author William Theo
* @date 24/4/2025
*/
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserCredentialService userCredentialService;

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    public AuthServiceImpl(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserCredentialService userCredentialService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userCredentialService = userCredentialService;
    }

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {

        log.info("Authentication Service: authenticating user {}", loginRequestDTO.getEmail());

        try {
            UserCredentialDTO userCredentialDTO = userCredentialService.selectByEmail(loginRequestDTO.getEmail());

            // Add password validation
            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userCredentialDTO.getPasswordHash())) {
                log.warn("Authentication failed for email: {}", loginRequestDTO.getEmail());
                throw new BadCredentialsException("Invalid email or password");
            }

            String token = jwtUtil.generateToken(userCredentialDTO.getEmail(), userCredentialDTO.getUserRole().name());
            return new LoginResponseDTO(token, userCredentialDTO.getUserId(), userCredentialDTO.getUserRole());
        } catch (UserCredentialNotFoundException e) {
            log.warn("User not found with email: {}", loginRequestDTO.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @Override
    public boolean validateToken(String token) {
        try{
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }
}
