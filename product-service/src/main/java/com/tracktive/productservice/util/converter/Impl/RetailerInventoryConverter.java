package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.entity.RetailerInventory;

import java.util.Objects;

/**
* Description: Util for convert Retailer Inventory Entity
* @author William Theo
* @date 10/3/2025
*/
public class RetailerInventoryConverter {

    // Private constructor to prevent instantiation
    private RetailerInventoryConverter() {
    }

    public static RetailerInventoryDTO toDTO(RetailerInventory retailerInventory) {
        if (Objects.isNull(retailerInventory)) {
            return null;
        }
        RetailerInventoryDTO dto = new RetailerInventoryDTO();
        dto.setRetailerInventoryId(retailerInventory.getRetailerInventoryId());
        dto.setRetailerId(retailerInventory.getRetailerId());
        dto.setProductId(retailerInventory.getProductId());
        dto.setStockQuantity(retailerInventory.getStockQuantity());
        dto.setReorderLevel(retailerInventory.getReorderLevel());
        dto.setProductStatus(retailerInventory.getProductStatus());
        dto.setUpdatedAt(retailerInventory.getUpdatedAt());
        dto.setCreatedAt(retailerInventory.getCreatedAt());
        return dto;
    }

    public static RetailerInventory toEntity(RetailerInventoryDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        RetailerInventory retailerInventory = new RetailerInventory();
        retailerInventory.setRetailerInventoryId(dto.getRetailerInventoryId());
        retailerInventory.setRetailerId(dto.getRetailerId());
        retailerInventory.setProductId(dto.getProductId());
        retailerInventory.setStockQuantity(dto.getStockQuantity());
        retailerInventory.setReorderLevel(dto.getReorderLevel());
        retailerInventory.setProductStatus(dto.getProductStatus());
        retailerInventory.setUpdatedAt(dto.getUpdatedAt());
        retailerInventory.setCreatedAt(dto.getCreatedAt());
        return retailerInventory;
    }
}
