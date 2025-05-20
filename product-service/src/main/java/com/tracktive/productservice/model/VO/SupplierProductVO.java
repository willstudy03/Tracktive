package com.tracktive.productservice.model.VO;


import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.Enum.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplierProductVO {

    private String supplierProductId;

    private String supplierId;

    private String productId;

    private BigDecimal price;

    private BigDecimal discountPercentage;

    private Integer stockQuantity;

    private ProductStatus productStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private ProductDTO productDTO;

    public SupplierProductVO() {
    }

    public SupplierProductVO(String supplierProductId, String supplierId, String productId, BigDecimal price, BigDecimal discountPercentage, Integer stockQuantity, ProductStatus productStatus, LocalDateTime updatedAt, LocalDateTime createdAt, ProductDTO productDTO) {
        this.supplierProductId = supplierProductId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.stockQuantity = stockQuantity;
        this.productStatus = productStatus;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.productDTO = productDTO;
    }

    public String getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(String supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
