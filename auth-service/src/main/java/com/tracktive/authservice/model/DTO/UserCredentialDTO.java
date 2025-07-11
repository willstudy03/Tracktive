package com.tracktive.authservice.model.DTO;

import com.tracktive.authservice.model.Enum.UserRole;
import com.tracktive.authservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
/**
* Description: User Credential DTO
* @author William Theo
* @date 23/4/2025
*/
public class UserCredentialDTO {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String passwordHash;

    @NotNull(message = "User role is required")
    @ValidEnum(enumClass = UserRole.class, message = "Invalid User Role")
    private UserRole userRole;

    @NotNull(message = "MustResetPassword flag is required")
    private boolean mustResetPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserCredentialDTO() {
    }

    public UserCredentialDTO(String userId, String email, String passwordHash, UserRole userRole, boolean mustResetPassword) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
        this.mustResetPassword = mustResetPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isMustResetPassword() {
        return mustResetPassword;
    }

    public void setMustResetPassword(boolean mustResetPassword) {
        this.mustResetPassword = mustResetPassword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
