package com.tracktive.userservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tracktive.userservice.model.Enum.UserRole;

/**
* Description: User Management Response DTO
* @author William Theo
* @date 23/4/2025
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserManagementResponseDTO {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private UserRole userRole;

    private RetailerDetailsDTO retailerDetailsDTO;

    private SupplierDetailsDTO supplierDetailsDTO;

    public UserManagementResponseDTO() {
    }

    public UserManagementResponseDTO(String id, String name, String email, String phoneNumber, UserRole userRole, RetailerDetailsDTO retailerDetailsDTO, SupplierDetailsDTO supplierDetailsDTO) {
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
