package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.NotBlank;

/**
* Description: Order Placement Request DTO
* @author William Theo
* @date 4/4/2025
*/
public class OrderPlacementRequestDTO {

    @NotBlank(message = "Retailer ID cannot be blank")
    String retailerId;

    @NotBlank(message = "Delivery Address ID cannot be blank")
    String deliveryAddress;

    public OrderPlacementRequestDTO() {
    }

    public OrderPlacementRequestDTO(String retailerId, String deliveryAddress) {
        this.retailerId = retailerId;
        this.deliveryAddress = deliveryAddress;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
