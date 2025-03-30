package com.tracktive.deliveryservice.model.DTO;

import com.tracktive.deliveryservice.model.Enum.DeliveryStatus;
import com.tracktive.deliveryservice.model.Enum.DeliveryType;
import com.tracktive.deliveryservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.*;

/**
* Description: Delivery Task Request DTO
* @author William Theo
* @date 30/3/2025
*/
public class DeliveryTaskRequestDTO {

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

    @NotNull(message = "Delivery Status is required")
    @ValidEnum(enumClass = DeliveryStatus.class, message = "Invalid Delivery Status")
    private DeliveryStatus deliveryStatus;

    public DeliveryTaskRequestDTO() {
    }

    public @NotBlank(message = "Order ID is required") String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotBlank(message = "Order ID is required") String orderId) {
        this.orderId = orderId;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public @NotBlank(message = "Recipient ID is required") String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(@NotBlank(message = "Recipient ID is required") String recipientId) {
        this.recipientId = recipientId;
    }

    public @NotNull(message = "Delivery type is required") DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(@NotNull(message = "Delivery type is required") DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public @NotBlank(message = "Pick-up address cannot be blank") @Size(max = 255, message = "Pick-up address cannot exceed 255 characters") String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(@NotBlank(message = "Pick-up address cannot be blank") @Size(max = 255, message = "Pick-up address cannot exceed 255 characters") String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public @NotBlank(message = "Destination address cannot be blank") @Size(max = 255, message = "Destination address cannot exceed 255 characters") String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(@NotBlank(message = "Destination address cannot be blank") @Size(max = 255, message = "Destination address cannot exceed 255 characters") String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public @NotNull(message = "Delivery Status is required") DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(@NotNull(message = "Delivery Status is required") DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
