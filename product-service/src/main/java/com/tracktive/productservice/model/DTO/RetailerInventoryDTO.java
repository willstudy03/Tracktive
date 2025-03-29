package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
* Description: Retailer Inventory DTO
* @author William Theo
* @date 10/3/2025
*/
public class RetailerInventoryDTO {

    @NotBlank(message = "Retailer Inventory ID is required")
    private String retailerInventoryId;

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

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public RetailerInventoryDTO() {
    }

    public RetailerInventoryDTO(String retailerInventoryId, String retailerId, String productId, Integer stockQuantity, Integer reorderLevel, ProductStatus productStatus) {
        this.retailerInventoryId = retailerInventoryId;
        this.retailerId = retailerId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.productStatus = productStatus;
    }

    public String getRetailerInventoryId() {
        return retailerInventoryId;
    }

    public void setRetailerInventoryId(String retailerInventoryId) {
        this.retailerInventoryId = retailerInventoryId;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
