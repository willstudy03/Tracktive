package com.tracktive.productservice.util;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.entity.Product;

import java.util.Objects;

/**
* Description: Util for convert Product Model
* @author William Theo
* @date 7/3/2025
*/
public class ProductConverter {
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
}
