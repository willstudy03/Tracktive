package com.tracktive.authservice.model.DTO;

import com.tracktive.authservice.model.Enum.UserRole;
import com.tracktive.authservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "User role is required")
    @ValidEnum(enumClass = UserRole.class, message = "Invalid User Role")
    private UserRole userRole;

    public UserCredentialRequestDTO() {
    }

    public UserCredentialRequestDTO(String userId, String email, UserRole userRole) {
        this.userId = userId;
        this.email = email;
        this.userRole = userRole;
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

    public @NotNull(message = "User role is required") UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NotNull(message = "User role is required") UserRole userRole) {
        this.userRole = userRole;
    }
}
