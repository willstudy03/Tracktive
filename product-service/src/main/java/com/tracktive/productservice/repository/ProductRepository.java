package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.DTO.ProductDTO;

import java.util.List;
import java.util.Optional;

/**
* Description: Product Repository Interface
* @author William Theo
* @date 7/3/2025
*/
public interface ProductRepository {
    // Get all product
    List<ProductDTO> selectAllProducts();
    // Find product by ID
    Optional<ProductDTO> selectProductById(String id);
    // Lock a product by ID
    Optional<ProductDTO> lockProductById(String id);
    // Insert a new product
    boolean addProduct(ProductDTO productDTO);
    // Update product info
    boolean updateProduct(ProductDTO productDTO);
    // Delete product by ID
    boolean deleteById(String id);
}
