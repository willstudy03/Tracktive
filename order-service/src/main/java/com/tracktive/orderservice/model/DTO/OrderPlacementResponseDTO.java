package com.tracktive.orderservice.model.DTO;
/**
* Description: Order Placement Response DTO
* @author William Theo
* @date 4/4/2025
*/
public class OrderPlacementResponseDTO {

    String orderId;

    public OrderPlacementResponseDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
