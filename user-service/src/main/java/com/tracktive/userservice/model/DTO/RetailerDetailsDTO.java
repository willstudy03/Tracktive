package com.tracktive.userservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
* Description: Retailer Details DTO
* @author William Theo
* @date 23/4/2025
*/
public class RetailerDetailsDTO {

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

    public RetailerDetailsDTO() {
    }

    public RetailerDetailsDTO(String ssmRegistrationNumber, String businessName, String businessAddress, String bankAccount, String bankName) {
        this.ssmRegistrationNumber = ssmRegistrationNumber;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
    }

    public @NotBlank(message = "SSM Registration Number is required") @Pattern(regexp = "^[0-9]{12}$", message = "SSM Registration Number must be 12 digits") String getSsmRegistrationNumber() {
        return ssmRegistrationNumber;
    }

    public void setSsmRegistrationNumber(@NotBlank(message = "SSM Registration Number is required") @Pattern(regexp = "^[0-9]{12}$", message = "SSM Registration Number must be 12 digits") String ssmRegistrationNumber) {
        this.ssmRegistrationNumber = ssmRegistrationNumber;
    }

    public @NotBlank(message = "Business name is required") @Size(max = 100, message = "Business name must not exceed 100 characters") String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(@NotBlank(message = "Business name is required") @Size(max = 100, message = "Business name must not exceed 100 characters") String businessName) {
        this.businessName = businessName;
    }

    public @NotBlank(message = "Business address is required") String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(@NotBlank(message = "Business address is required") String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public @NotBlank(message = "Bank account is required") @Pattern(regexp = "^[0-9]{10,16}$", message = "Bank account must be between 10 and 16 digits") String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(@NotBlank(message = "Bank account is required") @Pattern(regexp = "^[0-9]{10,16}$", message = "Bank account must be between 10 and 16 digits") String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public @NotBlank(message = "Bank name is required") String getBankName() {
        return bankName;
    }

    public void setBankName(@NotBlank(message = "Bank name is required") String bankName) {
        this.bankName = bankName;
    }
}
