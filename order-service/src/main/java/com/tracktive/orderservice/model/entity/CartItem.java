package com.tracktive.orderservice.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Shopping Cart POJO
* @author William Theo
* @date 14/3/2025
*/
public class CartItem {

    private String id;

    private String retailerId;

    private String supplierProductId;

    private String supplierId;

    private String productId;

    private Integer quantity;

    private BigDecimal priceSnapshot;

    private BigDecimal discountSnapshot;

    private BigDecimal subtotal;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public CartItem() {
    }

    public CartItem(String id, String retailerId, String supplierProductId, String supplierId, String productId, Integer quantity, BigDecimal priceSnapshot, BigDecimal discountSnapshot, BigDecimal subtotal) {
        this.id = id;
        this.retailerId = retailerId;
        this.supplierProductId = supplierProductId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceSnapshot = priceSnapshot;
        this.discountSnapshot = discountSnapshot;
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceSnapshot() {
        return priceSnapshot;
    }

    public void setPriceSnapshot(BigDecimal priceSnapshot) {
        this.priceSnapshot = priceSnapshot;
    }

    public BigDecimal getDiscountSnapshot() {
        return discountSnapshot;
    }

    public void setDiscountSnapshot(BigDecimal discountSnapshot) {
        this.discountSnapshot = discountSnapshot;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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
