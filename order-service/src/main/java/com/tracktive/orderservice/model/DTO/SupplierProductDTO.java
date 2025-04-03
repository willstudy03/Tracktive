package com.tracktive.orderservice.model.DTO;

import stock_management.ProductStatus;
/**
* Description: Supplier Product DTO for stock management
* @author William Theo
* @date 3/4/2025
*/
public class SupplierProductDTO {
    private String supplierProductId;
    private String supplierId;
    private String productId;
    private double price;
    private double discountPercentage;
    private int stockQuantity;
    private ProductStatus productStatus;

    public SupplierProductDTO() {
    }

    public SupplierProductDTO(String supplierProductId, String supplierId, String productId, double price, double discountPercentage, int stockQuantity, ProductStatus productStatus) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
