package com.tracktive.productservice.model.entity;

import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Product POJO
* @author William Theo
* @date 7/3/2025
*/
public class Product {

    private String productId;

    private ProductCategory productCategory;

    private String productBrand;

    private String productName;

    private String productDescription;

    private BigDecimal recommendedPrice;

    private String imageUrl;

    private ProductStatus productStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public Product() {
    }

    public Product(String productId, ProductCategory productCategory, String productBrand, String productName, String productDescription, BigDecimal recommendedPrice, String imageUrl, ProductStatus productStatus) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.productName = productName;
        this.productDescription = productDescription;
        this.recommendedPrice = recommendedPrice;
        this.imageUrl = imageUrl;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(BigDecimal recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
