package com.tracktive.authservice.model.DTO;

import java.time.LocalDateTime;
/**
* Description: User Credential DTO
* @author William Theo
* @date 23/4/2025
*/
public class UserCredentialDTO {

    private String userId;

    private String email;

    private String passwordHash;

    private boolean mustResetPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserCredentialDTO() {
    }

    public UserCredentialDTO(String userId, String email, String passwordHash, boolean mustResetPassword) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
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
