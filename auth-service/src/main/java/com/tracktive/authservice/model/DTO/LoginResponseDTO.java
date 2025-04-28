package com.tracktive.authservice.model.DTO;

import com.tracktive.authservice.model.Enum.UserRole;

/**
* Description: Login Response DTO
* @author William Theo
* @date 24/4/2025
*/
public class LoginResponseDTO {

    private String token;

    private String userId;

    private UserRole userRole;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String userId, UserRole userRole) {
        this.token = token;
        this.userId = userId;
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
