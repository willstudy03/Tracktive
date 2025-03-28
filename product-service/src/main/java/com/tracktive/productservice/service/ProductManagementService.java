package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;

import java.util.List;

/**
* Description: Product Management Service Interface
* @author William Theo
* @date 25/3/2025
*/
public interface ProductManagementService {

    List<ProductManagementDTO> selectAllProducts();

    ProductManagementDTO selectProductById(String productId);

    ProductManagementDTO createProduct(ProductManagementRequestDTO productManagementRequestDTO);

    ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO);

    ProductManagementDTO deleteProduct(String productId);
}
