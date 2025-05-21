package com.tracktive.productservice.model.VO;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.Enum.ProductStatus;

import java.time.LocalDateTime;

public class RetailerInventoryVO {

    private String retailerInventoryId;

    private String retailerId;

    private String productId;

    private Integer stockQuantity;

    private Integer reorderLevel;

    private ProductStatus productStatus;

    private ProductDTO productDTO;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public RetailerInventoryVO() {
    }

    public RetailerInventoryVO(String retailerInventoryId, String retailerId, String productId, Integer stockQuantity, Integer reorderLevel, ProductStatus productStatus, ProductDTO productDTO, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.retailerInventoryId = retailerInventoryId;
        this.retailerId = retailerId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.productStatus = productStatus;
        this.productDTO = productDTO;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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
