package com.tracktive.userservice.model.entity;

/**
* Description: Retailer POJO
* @author William Theo
* @date 1/3/2025
*/
public class Retailer{

    private String retailerId;

    private String ssmRegistrationNumber;

    private String businessName;

    private String businessAddress;

    private String bankAccount;

    private String bankName;

    private Integer payByTermCredit;

    public Retailer() {
    }

    public Retailer(String retailerId, String ssmRegistrationNumber, String businessName, String businessAddress, Integer payByTermCredit) {
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
