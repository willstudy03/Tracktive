package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* Description: Retailer Inventory Request DTO
* @author William Theo
* @date 29/3/2025
*/
public class RetailerInventoryRequestDTO {

    @NotBlank(message = "Retailer ID is required")
    private String retailerId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Reorder level is required")
    @Min(value = 0, message = "Reorder level cannot be negative")
    private Integer reorderLevel;

    @NotNull(message = "Product status is required")
    @ValidEnum(enumClass = ProductStatus.class, message = "Invalid Product Status")
    private ProductStatus productStatus;


    public RetailerInventoryRequestDTO() {
    }

    public RetailerInventoryRequestDTO(String retailerId, String productId, Integer stockQuantity, Integer reorderLevel, ProductStatus productStatus) {
        this.retailerId = retailerId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.productStatus = productStatus;
    }

    public @NotBlank(message = "Retailer ID is required") String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(@NotBlank(message = "Retailer ID is required") String retailerId) {
        this.retailerId = retailerId;
    }

    public @NotBlank(message = "Product ID is required") String getProductId() {
        return productId;
    }

    public void setProductId(@NotBlank(message = "Product ID is required") String productId) {
        this.productId = productId;
    }

    public @NotNull(message = "Stock quantity is required") @Min(value = 0, message = "Stock quantity cannot be negative") Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@NotNull(message = "Stock quantity is required") @Min(value = 0, message = "Stock quantity cannot be negative") Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public @NotNull(message = "Reorder level is required") @Min(value = 0, message = "Reorder level cannot be negative") Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(@NotNull(message = "Reorder level is required") @Min(value = 0, message = "Reorder level cannot be negative") Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public @NotNull(message = "Product status is required") ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(@NotNull(message = "Product status is required") ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
