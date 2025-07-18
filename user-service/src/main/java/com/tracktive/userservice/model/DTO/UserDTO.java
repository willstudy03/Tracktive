package com.tracktive.userservice.model.DTO;

import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

/**
* Description: User DTO
* @author William Theo
* @date 3/3/2025
*/
public class UserDTO {

    @NotBlank(message = "User ID is required")
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "Phone number must be a valid Malaysian number")
    private String phoneNumber;

    @NotNull(message = "User role is required")
    @ValidEnum(enumClass = UserRole.class, message = "Invalid User Role")
    private UserRole userRole;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String email, String phoneNumber, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
