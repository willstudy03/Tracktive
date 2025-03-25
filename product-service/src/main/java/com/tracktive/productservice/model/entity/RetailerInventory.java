package com.tracktive.productservice.model.entity;

import com.tracktive.productservice.model.Enum.ProductStatus;

import java.time.LocalDateTime;

/**
* Description: Retailer Inventory POJO
* @author William Theo
* @date 10/3/2025
*/
public class RetailerInventory {

    private String retailerInventoryId;

    private String retailerId;

    private String productId;

    private Integer stockQuantity;

    private Integer reorderLevel;

    private ProductStatus productStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public RetailerInventory() {
    }

    public RetailerInventory(String retailerInventoryId, String retailerId, String productId, Integer stockQuantity, Integer reorderLevel, ProductStatus productStatus) {
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
