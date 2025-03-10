package com.tracktive.productservice.model.entity;

import com.tracktive.productservice.model.Enum.ProductStatus;

import java.math.BigDecimal;

/**
* Description: Supplier Product POJO
* @author William Theo
* @date 10/3/2025
*/
public class SupplierProduct {

    private String supplierProductId;

    private String supplierId;

    private String productId;

    private BigDecimal price;

    private BigDecimal discountPercentage;

    private Integer stockQuantity;

    private ProductStatus productStatus;

    private String updatedAt;

    private String createdAt;

    public SupplierProduct() {
    }

    public SupplierProduct(String supplierProductId, String supplierId, String productId, BigDecimal price, BigDecimal discountPercentage, Integer stockQuantity, ProductStatus productStatus) {
        this.supplierProductId = supplierProductId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.stockQuantity = stockQuantity;
        this.productStatus = productStatus;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
