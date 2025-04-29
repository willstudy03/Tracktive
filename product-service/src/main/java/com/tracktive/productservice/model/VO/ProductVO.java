package com.tracktive.productservice.model.VO;

import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;

import java.math.BigDecimal;

/**
* Description: Product Response VO
* @author William Theo
* @date 25/3/2025
*/
public class ProductVO {
    private String productId;

    private ProductCategory productCategory;

    private String productBrand;

    private String productName;

    private String productDescription;

    private BigDecimal recommendedPrice;

    private String imageUrl;

    private ProductStatus productStatus;

    public ProductVO() {
    }

    public ProductVO(String productId, ProductCategory productCategory, String productBrand, String productName, String productDescription, BigDecimal recommendedPrice, String imageUrl, ProductStatus productStatus) {
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
}
