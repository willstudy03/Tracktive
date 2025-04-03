package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
* Description: Cart Item Management Request DTO
* @author William Theo
* @date 3/4/2025
*/
public class CartItemManagementRequestDTO {

    @NotBlank(message = "Retailer ID cannot be blank")
    String retailerId;

    @NotBlank(message = "Supplier Product ID cannot be blank")
    String supplierProductId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    Integer quantity;

    public CartItemManagementRequestDTO() {
    }

    public CartItemManagementRequestDTO(String retailerId, String supplierProductId, Integer quantity) {
        this.retailerId = retailerId;
        this.supplierProductId = supplierProductId;
        this.quantity = quantity;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(String supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
