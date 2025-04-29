package com.tracktive.productservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Product Management DTO
* @author William Theo
* @date 25/3/2025
*/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "productCategory", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductManagementTireDTO.class, name = "TIRE")
})
public class ProductManagementDTO {

    @NotBlank(message = "Product ID cannot be blank")
    private String productId;

    @NotNull(message = "Product Category is required")
    @ValidEnum(enumClass = ProductCategory.class, message = "Invalid Product Category")
    private ProductCategory productCategory;

    @NotBlank(message = "Product Brand is required")
    @Size(max = 100, message = "Product Brand cannot exceed 100 characters")
    private String productBrand;

    @NotBlank(message = "Product Name is required")
    @Size(max = 150, message = "Product Name cannot exceed 150 characters")
    private String productName;

    @Size(max = 500, message = "Product Description cannot exceed 500 characters")
    private String productDescription;

    @NotNull(message = "Price is required")  // Use NotNull for BigDecimal
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater or equal than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid decimal with up to 10 digits and 2 decimal places")
    private BigDecimal recommendedPrice;

    private String imageUrl;

    @NotNull(message = "Product Status is required")
    @ValidEnum(enumClass = ProductStatus.class, message = "Invalid Product Status")
    private ProductStatus productStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public ProductManagementDTO() {
    }

    public ProductManagementDTO(String productId, ProductCategory productCategory, String productBrand, String productName, String productDescription, BigDecimal recommendedPrice, String imageUrl, ProductStatus productStatus) {
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
