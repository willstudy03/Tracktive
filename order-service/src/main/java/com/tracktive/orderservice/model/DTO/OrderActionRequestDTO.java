package com.tracktive.orderservice.model.DTO;
/**
* Description: Order Placement Request DTO
* @author William Theo
* @date 4/4/2025
*/
public class OrderActionRequestDTO {

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
