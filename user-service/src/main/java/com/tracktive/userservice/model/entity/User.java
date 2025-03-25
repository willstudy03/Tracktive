package com.tracktive.userservice.model.entity;

import com.tracktive.userservice.model.Enum.UserRole;

import java.time.LocalDateTime;

/**
* Description: User POJO
* @author William Theo
* @date 28/2/2025
*/
public class User {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private UserRole userRole;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public User() {}

    public User(String id, String name, String email, String phoneNumber, UserRole userRole) {
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
