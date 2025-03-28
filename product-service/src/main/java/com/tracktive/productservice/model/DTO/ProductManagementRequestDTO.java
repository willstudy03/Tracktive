package com.tracktive.productservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;

import java.math.BigDecimal;

/**
* Description: Product Management Request DTO
* @author William Theo
* @date 28/3/2025
*/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "productCategory", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductManagementRequestTireDTO.class, name = "TIRE")
})

public class ProductManagementRequestDTO {

    private ProductCategory productCategory;

    private String productBrand;

    private String productName;

    private String productDescription;

    private BigDecimal recommendedPrice;

    private ProductStatus productStatus;

    public ProductManagementRequestDTO() {
    }

    public ProductManagementRequestDTO(ProductCategory productCategory, String productBrand, String productName, String productDescription, BigDecimal recommendedPrice, ProductStatus productStatus) {
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
