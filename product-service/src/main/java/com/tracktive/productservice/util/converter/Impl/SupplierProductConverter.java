package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductRequestDTO;
import com.tracktive.productservice.model.VO.SupplierProductVO;
import com.tracktive.productservice.model.entity.SupplierProduct;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Supplier Product Model
* @author William Theo
* @date 10/3/2025
*/
public class SupplierProductConverter {

    // Private constructor to prevent instantiation
    private SupplierProductConverter() {
    }

    public static SupplierProductDTO toDTO(SupplierProduct supplierProduct) {
        if (Objects.isNull(supplierProduct)) {
            return null;
        }
        SupplierProductDTO dto = new SupplierProductDTO();
        dto.setSupplierProductId(supplierProduct.getSupplierProductId());
        dto.setSupplierId(supplierProduct.getSupplierId());
        dto.setProductId(supplierProduct.getProductId());
        dto.setPrice(supplierProduct.getPrice());
        dto.setDiscountPercentage(supplierProduct.getDiscountPercentage());
        dto.setStockQuantity(supplierProduct.getStockQuantity());
        dto.setProductStatus(supplierProduct.getProductStatus());
        dto.setUpdatedAt(supplierProduct.getUpdatedAt());
        dto.setCreatedAt(supplierProduct.getCreatedAt());
        return dto;
    }

    public static SupplierProductDTO toDTO(SupplierProductRequestDTO supplierProductRequestDTO){
        if (Objects.isNull(supplierProductRequestDTO)){
            return null;
        }
        SupplierProductDTO dto = new SupplierProductDTO();
        dto.setSupplierProductId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(supplierProductRequestDTO, dto);
        return  dto;
    }

    public static SupplierProductVO toVO(SupplierProductDTO supplierProductDTO, ProductDTO productDTO) {
        if (Objects.isNull(supplierProductDTO)) {
            return null;
        }
        SupplierProductVO vo = new SupplierProductVO();
        BeanUtils.copyProperties(supplierProductDTO, vo);
        vo.setProductDTO(productDTO);
        return vo;
    }

    public static SupplierProduct toEntity(SupplierProductDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplierProductId(dto.getSupplierProductId());
        supplierProduct.setSupplierId(dto.getSupplierId());
        supplierProduct.setProductId(dto.getProductId());
        supplierProduct.setPrice(dto.getPrice());
        supplierProduct.setDiscountPercentage(dto.getDiscountPercentage());
        supplierProduct.setStockQuantity(dto.getStockQuantity());
        supplierProduct.setProductStatus(dto.getProductStatus());
        supplierProduct.setUpdatedAt(dto.getUpdatedAt());
        supplierProduct.setCreatedAt(dto.getCreatedAt());
        return supplierProduct;
    }
}
