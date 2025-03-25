package com.tracktive.deliveryservice.model.DTO;

import com.tracktive.deliveryservice.model.Enum.DeliveryStatus;
import com.tracktive.deliveryservice.model.Enum.DeliveryType;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
* Description: Delivery Task DTO
* @author William Theo
* @date 21/3/2025
*/
public class DeliveryTaskDTO {
    private String id;

    private String orderId;

    private String courierId;

    private String recipientId;

    private DeliveryType deliveryType;

    private String pickUpAddress;

    private String destinationAddress;

    private Double currentLatitude;

    private Double currentLongitude;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

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
