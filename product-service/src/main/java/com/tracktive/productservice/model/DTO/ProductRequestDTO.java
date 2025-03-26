package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequestDTO {

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


    @NotNull(message = "Product Status is required")
    @ValidEnum(enumClass = ProductStatus.class, message = "Invalid Product Status")
    private ProductStatus productStatus;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(ProductCategory productCategory, String productBrand, String productName, String productDescription, BigDecimal recommendedPrice, ProductStatus productStatus) {
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.productName = productName;
        this.productDescription = productDescription;
        this.recommendedPrice = recommendedPrice;
        this.productStatus = productStatus;
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

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
