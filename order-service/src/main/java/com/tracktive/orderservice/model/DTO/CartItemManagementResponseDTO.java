package com.tracktive.orderservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
/**
* Description: Cart Item Management Response DTO
* @author William Theo
* @date 3/4/2025
*/
public class CartItemManagementResponseDTO {

    @NotBlank(message = "CartItem ID is required")
    private String id;

    @NotBlank(message = "Retailer ID cannot be blank")
    private String retailerId;

    @NotBlank(message = "Supplier Product ID cannot be blank")
    private String supplierProductId;

    private String supplierId;

    private String productId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    private BigDecimal priceSnapshot;

    private BigDecimal discountSnapshot;

    private BigDecimal subtotal;

    public CartItemManagementResponseDTO() {
    }

    public CartItemManagementResponseDTO(String id, String retailerId, String supplierProductId, String supplierId, String productId, Integer quantity, BigDecimal priceSnapshot, BigDecimal discountSnapshot, BigDecimal subtotal) {
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
}
