package com.tracktive.userservice.model.DTO;

/**
* Description: Courier DTO
* @author William Theo
* @date 4/3/2025
*/
public class CourierDTO {
    private String courierId;

    private String drivingLicenseNumber;

    private String plateNumber;

    private String preferredDeliveryZone;

    private String bankAccount;

    private String bankName;

    public CourierDTO() {
    }

    public CourierDTO(String courierId, String drivingLicenseNumber, String plateNumber, String preferredDeliveryZone, String bankAccount, String bankName) {
        this.courierId = courierId;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.plateNumber = plateNumber;
        this.preferredDeliveryZone = preferredDeliveryZone;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPreferredDeliveryZone() {
        return preferredDeliveryZone;
    }

    public void setPreferredDeliveryZone(String preferredDeliveryZone) {
        this.preferredDeliveryZone = preferredDeliveryZone;
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
