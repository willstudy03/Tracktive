package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.NotBlank;

/**
* Description: Order Placement Request DTO
* @author William Theo
* @date 4/4/2025
*/
public class OrderActionRequestDTO {

    @NotBlank(message = "Retailer ID cannot be blank")
    String retailerId;

    public OrderActionRequestDTO() {
    }

    public OrderActionRequestDTO(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }
}
