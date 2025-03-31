package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
* Description: Order Item Request DTO
* @author William Theo
* @date 30/3/2025
*/
public class OrderItemRequestDTO {

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotBlank(message = "Supplier Product ID is required")
    private String supplierProductId;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price snapshot is required")
    @DecimalMin(value = "0.01", message = "Price snapshot must be greater than 0")
    private BigDecimal priceSnapshot;

    @NotNull(message = "Discount snapshot is required")
    @DecimalMin(value = "0.00", message = "Discount snapshot cannot be negative")
    @DecimalMax(value = "100.00", message = "Discount snapshot cannot exceed 100")
    private BigDecimal discountSnapshot;

    @NotNull(message = "Subtotal is required")
    @DecimalMin(value = "0.00", message = "Subtotal cannot be negative")
    private BigDecimal subtotal;

    public OrderItemRequestDTO() {
    }

    public OrderItemRequestDTO(String orderId, String supplierProductId, String supplierId, String productId, Integer quantity, BigDecimal priceSnapshot, BigDecimal discountSnapshot, BigDecimal subtotal) {
        this.orderId = orderId;
        this.supplierProductId = supplierProductId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceSnapshot = priceSnapshot;
        this.discountSnapshot = discountSnapshot;
        this.subtotal = subtotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
