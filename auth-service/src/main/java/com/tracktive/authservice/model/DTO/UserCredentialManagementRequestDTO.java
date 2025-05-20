package com.tracktive.authservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
* Description: User Credential Management Request DTO
* @author William Theo
* @date 28/4/2025
*/
public class UserCredentialManagementRequestDTO {

    @NotBlank(message = "User ID must not be blank")
    private String userId;

    private String currentPassword;

    @NotBlank(message = "New password must not be blank")
    @Size(min = 8, max = 100, message = "New password must be between 8 and 100 characters")
    private String newPassword;

    public UserCredentialManagementRequestDTO() {
    }

    public UserCredentialManagementRequestDTO(String userId, String currentPassword, String newPassword) {
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
