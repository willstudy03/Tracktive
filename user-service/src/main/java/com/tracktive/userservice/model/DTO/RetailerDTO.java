package com.tracktive.userservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
* Description: RetailerDTO
* @author William Theo
* @date 4/3/2025
*/
public class RetailerDTO {

    private String retailerId;

    @NotBlank(message = "SSM Registration Number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "SSM Registration Number must be 12 digits")
    private String ssmRegistrationNumber;

    @NotBlank(message = "Business name is required")
    @Size(max = 100, message = "Business name must not exceed 100 characters")
    private String businessName;

    @NotBlank(message = "Business address is required")
    private String businessAddress;

    @NotBlank(message = "Bank account is required")
    @Pattern(regexp = "^[0-9]{10,16}$", message = "Bank account must be between 10 and 16 digits")
    private String bankAccount;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    private Integer payByTermCredit;

    public RetailerDTO() {
    }

    public RetailerDTO(String retailerId, String ssmRegistrationNumber, String businessName, String businessAddress, Integer payByTermCredit) {
        this.retailerId = retailerId;
        this.ssmRegistrationNumber = ssmRegistrationNumber;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.payByTermCredit = payByTermCredit;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getSsmRegistrationNumber() {
        return ssmRegistrationNumber;
    }

    public void setSsmRegistrationNumber(String ssmRegistrationNumber) {
        this.ssmRegistrationNumber = ssmRegistrationNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getPayByTermCredit() {
        return payByTermCredit;
    }

    public void setPayByTermCredit(Integer payByTermCredit) {
        this.payByTermCredit = payByTermCredit;
    }
}
