package com.tracktive.deliveryservice.model.DTO;

import com.tracktive.deliveryservice.model.Enum.DeliveryStatus;
import com.tracktive.deliveryservice.model.Enum.DeliveryType;
import com.tracktive.deliveryservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
* Description: Delivery Task DTO
* @author William Theo
* @date 21/3/2025
*/
public class DeliveryTaskDTO {

    @NotBlank(message = "Delivery Task ID is required")
    private String id;

    @NotBlank(message = "Order ID is required")
    private String orderId;

    private String courierId;

    @NotBlank(message = "Recipient ID is required")
    private String recipientId;

    @NotNull(message = "Delivery type is required")
    @ValidEnum(enumClass = DeliveryType.class, message = "Invalid Delivery Type")
    private DeliveryType deliveryType;

    @NotBlank(message = "Pick-up address cannot be blank")
    @Size(max = 255, message = "Pick-up address cannot exceed 255 characters")
    private String pickUpAddress;

    @NotBlank(message = "Destination address cannot be blank")
    @Size(max = 255, message = "Destination address cannot exceed 255 characters")
    private String destinationAddress;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double currentLatitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double currentLongitude;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @NotNull(message = "Delivery Status is required")
    @ValidEnum(enumClass = DeliveryStatus.class, message = "Invalid Delivery Status")
    private DeliveryStatus deliveryStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public DeliveryTaskDTO() {
    }

    public DeliveryTaskDTO(String id, String orderId, String courierId, String recipientId, DeliveryType deliveryType, String pickUpAddress, String destinationAddress, Double currentLatitude, Double currentLongitude, LocalDateTime startedAt, LocalDateTime completedAt, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.courierId = courierId;
        this.recipientId = recipientId;
        this.deliveryType = deliveryType;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.deliveryStatus = deliveryStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
