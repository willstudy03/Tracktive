package com.tracktive.productservice.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* Description: Stock Item DTO
* @author William Theo
* @date 31/3/2025
*/
public class StockItemDTO {

    @NotBlank(message = "Product ID is required")
    private String supplierProductDTO;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public StockItemDTO() {
    }

    public StockItemDTO(String supplierProductDTO, Integer quantity) {
        this.supplierProductDTO = supplierProductDTO;
        this.quantity = quantity;
    }

    public @NotBlank(message = "Product ID is required") String getSupplierProductDTO() {
        return supplierProductDTO;
    }

    public void setSupplierProductDTO(@NotBlank(message = "Product ID is required") String supplierProductDTO) {
        this.supplierProductDTO = supplierProductDTO;
    }

    public @NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be at least 1") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be at least 1") Integer quantity) {
        this.quantity = quantity;
    }
}
