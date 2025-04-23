package com.tracktive.userservice.model.DTO;

import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.util.annotation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
* Description: User Management Request DTO
* @author William Theo
* @date 23/4/2025
*/
public class UserManagementRequestDTO {

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

    @Valid
    private RetailerDetailsDTO retailerDetailsDTO;
    
    @Valid
    private SupplierDetailsDTO supplierDetailsDTO;

    public UserManagementRequestDTO() {
    }

    public UserManagementRequestDTO(String id, String name, String email, String phoneNumber, UserRole userRole, RetailerDetailsDTO retailerDetailsDTO, SupplierDetailsDTO supplierDetailsDTO) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.retailerDetailsDTO = retailerDetailsDTO;
        this.supplierDetailsDTO = supplierDetailsDTO;
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

    public RetailerDetailsDTO getRetailerDetailsDTO() {
        return retailerDetailsDTO;
    }

    public void setRetailerDetailsDTO(RetailerDetailsDTO retailerDetailsDTO) {
        this.retailerDetailsDTO = retailerDetailsDTO;
    }

    public SupplierDetailsDTO getSupplierDetailsDTO() {
        return supplierDetailsDTO;
    }

    public void setSupplierDetailsDTO(SupplierDetailsDTO supplierDetailsDTO) {
        this.supplierDetailsDTO = supplierDetailsDTO;
    }
}
