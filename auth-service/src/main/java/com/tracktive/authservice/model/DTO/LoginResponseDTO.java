package com.tracktive.authservice.model.DTO;

/**
* Description: Login Response DTO
* @author William Theo
* @date 24/4/2025
*/
public class LoginResponseDTO {

    private String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
