package com.tracktive.authservice.model.DTO;
/**
* Description: User Credential Management Response DTO
* @author William Theo
* @date 28/4/2025
*/
public class UserCredentialManagementResponseDTO {

    private String userId;

    private Boolean updateStatus;

    public UserCredentialManagementResponseDTO() {
    }

    public UserCredentialManagementResponseDTO(String userId, Boolean updateStatus) {
        this.userId = userId;
        this.updateStatus = updateStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Boolean updateStatus) {
        this.updateStatus = updateStatus;
    }
}
