package com.tracktive.userservice.model.DTO;

/**
* Description: Supplier DTO
* @author William Theo
* @date 4/3/2025
*/
public class SupplierDTO {

    private String supplierId;

    private String ssmRegistrationNumber;

    private String businessName;

    private String businessAddress;

    private String bankAccount;

    private String bankName;

    public SupplierDTO() {
    }

    public SupplierDTO(String supplierId, String ssmRegistrationNumber, String businessName, String businessAddress, String bankAccount, String bankName) {
        this.supplierId = supplierId;
        this.ssmRegistrationNumber = ssmRegistrationNumber;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
}
