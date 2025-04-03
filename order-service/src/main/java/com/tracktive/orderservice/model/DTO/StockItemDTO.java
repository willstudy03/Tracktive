package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* Description: Stock Item DTO
* @author William Theo
* @date 2/4/2025
*/
public class StockItemDTO {

    @NotBlank(message = "Product ID is required")
    private String supplierProductID;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public StockItemDTO() {
    }

    public StockItemDTO(String supplierProductID, Integer quantity) {
        this.supplierProductID = supplierProductID;
        this.quantity = quantity;
    }

    public String getSupplierProductID() {
        return supplierProductID;
    }

    public void setSupplierProductID(String supplierProductID) {
        this.supplierProductID = supplierProductID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
