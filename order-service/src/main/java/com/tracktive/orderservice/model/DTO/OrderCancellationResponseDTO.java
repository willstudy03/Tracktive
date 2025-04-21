package com.tracktive.orderservice.model.DTO;
/**
* Description: Order Cancellation Response DTO
* @author William Theo
* @date 21/4/2025
*/
public class OrderCancellationResponseDTO {
    String orderID;

    public OrderCancellationResponseDTO() {
    }

    public OrderCancellationResponseDTO(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
