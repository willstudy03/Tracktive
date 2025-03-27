package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;

import java.util.List;

/**
* Description: Product Management Service Interface
* @author William Theo
* @date 25/3/2025
*/
public interface ProductManagementService {

    List<ProductManagementDTO> selectAllProducts();

    ProductManagementDTO selectProductById(String productId);

    ProductManagementDTO createProduct(ProductManagementDTO productManagementDTO);

    ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO);

    ProductManagementDTO deleteProduct(String productId);
}
