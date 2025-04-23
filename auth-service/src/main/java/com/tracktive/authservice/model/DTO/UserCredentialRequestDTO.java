package com.tracktive.authservice.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
* Description: User Credential Request DTO
* @author William Theo
* @date 23/4/2025
*/
public class UserCredentialRequestDTO {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public UserCredentialRequestDTO() {
    }

    public UserCredentialRequestDTO(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public @NotBlank(message = "User ID is required") String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank(message = "User ID is required") String userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }
}
