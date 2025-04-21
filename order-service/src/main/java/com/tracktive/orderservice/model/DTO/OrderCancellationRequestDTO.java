package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.NotBlank;

/**
* Description: Order Cancellation Requesst DTO
* @author William Theo
* @date 21/4/2025
*/
public class OrderCancellationRequestDTO {

    @NotBlank(message = "Order ID cannot be blank")
    private String orderId;

    public OrderCancellationRequestDTO() {
    }

    public OrderCancellationRequestDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
