package com.tracktive.userservice.model.entity;

import com.tracktive.userservice.model.Enum.UserRole;
import jakarta.persistence.*;
/**
* Description: User POJO
* @author William Theo
* @date 28/2/2025
*/
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone_number;

    private UserRole userRole;

    private String created_at;

    public User() {}

    public User(String id, String name, String email, String phone_number, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }



}
