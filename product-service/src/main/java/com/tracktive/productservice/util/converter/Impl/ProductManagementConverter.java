package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementTireDTO;
import com.tracktive.productservice.model.DTO.TireDTO;

import java.util.Objects;

/**
* Description: Util for convert Product Management Model
* @author William Theo
* @date 27/3/2025
*/
public class ProductManagementConverter {

    // Private constructor to prevent instantiation
    private ProductManagementConverter() {
    }

    public static ProductManagementTireDTO toProductManagementTireDTO(ProductDTO productDTO, TireDTO tireDTO) {
        if (productDTO == null || tireDTO == null) {
            throw new IllegalArgumentException("ProductDTO and TireDTO cannot be null");
        }

        ProductManagementTireDTO dto = new ProductManagementTireDTO();

        // Map ProductDTO fields
        dto.setProductId(productDTO.getProductId());
        dto.setProductCategory(productDTO.getProductCategory());
        dto.setProductBrand(productDTO.getProductBrand());
        dto.setProductName(productDTO.getProductName());
        dto.setProductDescription(productDTO.getProductDescription());
        dto.setRecommendedPrice(productDTO.getRecommendedPrice());
        dto.setProductStatus(productDTO.getProductStatus());
        dto.setCreatedAt(productDTO.getCreatedAt());
        dto.setUpdatedAt(productDTO.getUpdatedAt());

        // Map TireDTO fields
        dto.setTireSku(tireDTO.getTireSku());
        dto.setWidth(tireDTO.getWidth());
        dto.setAspectRatio(tireDTO.getAspectRatio());
        dto.setRimDiameter(tireDTO.getRimDiameter());
        dto.setConstructionType(tireDTO.getConstructionType());
        dto.setLoadIndex(tireDTO.getLoadIndex());
        dto.setSpeedRating(tireDTO.getSpeedRating());
        dto.setTireSeason(tireDTO.getTireSeason());
        dto.setTreadPattern(tireDTO.getTreadPattern());
        dto.setTireType(tireDTO.getTireType());
        dto.setRunFlat(tireDTO.getRunFlat());

        return dto;
    }
}
