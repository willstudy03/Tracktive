package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.service.ProductManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Description: Product Management Service Implementation
* @author William Theo
* @date 25/3/2025
*/
@Service
public class ProductManagementServiceImpl implements ProductManagementService {
    @Override
    public List<ProductManagementDTO> selectAllProducts() {
        return List.of();
    }

    @Override
    public ProductManagementDTO selectProductById(String productId) {
        return null;
    }

    @Override
    public ProductManagementDTO createProduct(ProductManagementDTO productManagementDTO) {
        return null;
    }

    @Override
    public ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO) {
        return null;
    }

    @Override
    public ProductManagementDTO deleteProduct(String productId) {
        return null;
    }
}
