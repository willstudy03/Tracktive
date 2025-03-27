package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductRequestDTO;
import com.tracktive.productservice.model.VO.ProductVO;
import com.tracktive.productservice.model.entity.Product;

import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Product Model
* @author William Theo
* @date 7/3/2025
*/
public class ProductConverter {

    // Private constructor to prevent instantiation
    private ProductConverter(){}

    // Product Query
    public static ProductDTO toDTO(Product product){
        if (Objects.isNull(product)){
            return null;
        }
       ProductDTO productDTO = new ProductDTO();
       productDTO.setProductId(product.getProductId());
       productDTO.setProductCategory(product.getProductCategory());
       productDTO.setProductBrand(product.getProductBrand());
       productDTO.setProductName(product.getProductName());
       productDTO.setProductDescription(product.getProductDescription());
       productDTO.setRecommendedPrice(product.getRecommendedPrice());
       productDTO.setProductStatus(product.getProductStatus());
       productDTO.setUpdatedAt(product.getUpdatedAt());
       productDTO.setCreatedAt(product.getCreatedAt());
       return productDTO;
    }

    // Product Creation
    public static ProductDTO toDTO(ProductRequestDTO requestDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(UUID.randomUUID().toString());
        productDTO.setProductCategory(requestDTO.getProductCategory());
        productDTO.setProductBrand(requestDTO.getProductBrand());
        productDTO.setProductName(requestDTO.getProductName());
        productDTO.setProductDescription(requestDTO.getProductDescription());
        productDTO.setRecommendedPrice(requestDTO.getRecommendedPrice());
        productDTO.setProductStatus(requestDTO.getProductStatus());
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO){
        if (Objects.isNull(productDTO)){
            return null;
        }
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductBrand(productDTO.getProductBrand());
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setRecommendedPrice(productDTO.getRecommendedPrice());
        product.setProductStatus(productDTO.getProductStatus());
        product.setUpdatedAt(productDTO.getUpdatedAt());
        product.setCreatedAt(productDTO.getCreatedAt());
        return product;
    }

    public static ProductVO toVO(ProductDTO productDTO) {
        if (Objects.isNull(productDTO)) {
            return null;
        }
        ProductVO productVO = new ProductVO();
        productVO.setProductId(productDTO.getProductId());
        productVO.setProductCategory(productDTO.getProductCategory());
        productVO.setProductBrand(productDTO.getProductBrand());
        productVO.setProductName(productDTO.getProductName());
        productVO.setProductDescription(productDTO.getProductDescription());
        productVO.setRecommendedPrice(productDTO.getRecommendedPrice());
        productVO.setProductStatus(productDTO.getProductStatus());
        return productVO;
    }

    public static ProductManagementDTO toProductManagementDTO(ProductDTO productDTO){
        if (Objects.isNull(productDTO)){
            return null;
        }
        ProductManagementDTO productManagementDTO = new ProductManagementDTO();
        productManagementDTO.setProductId(productDTO.getProductId());
        productManagementDTO.setProductCategory(productDTO.getProductCategory());
        productManagementDTO.setProductBrand(productDTO.getProductBrand());
        productManagementDTO.setProductName(productDTO.getProductName());
        productManagementDTO.setProductDescription(productDTO.getProductDescription());
        productManagementDTO.setRecommendedPrice(productDTO.getRecommendedPrice());
        productManagementDTO.setProductStatus(productDTO.getProductStatus());
        productManagementDTO.setUpdatedAt(productDTO.getUpdatedAt());
        productManagementDTO.setCreatedAt(productDTO.getCreatedAt());
        return productManagementDTO;
    }

}
