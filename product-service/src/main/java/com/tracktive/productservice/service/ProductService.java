package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductRequestDTO;

import java.util.List;

/**
* Description: Product Service
* @author William Theo
* @date 11/3/2025
*/
public interface ProductService {
    // Get all product
    List<ProductDTO> selectAllProducts();
    // Find product by ID
    ProductDTO selectProductById(String id);
    // Lock a product by ID
    ProductDTO lockProductById(String id);
    // Insert a new product
    ProductDTO addProduct(ProductRequestDTO productRequestDTO);
    // Update product info
    ProductDTO updateProduct(ProductDTO productDTO);
    // Delete product by ID
    void deleteById(String id);
}
