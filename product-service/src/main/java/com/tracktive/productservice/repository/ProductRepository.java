package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.entity.Product;

import java.util.List;
import java.util.Optional;

/**
* Description: Product Repository Interface
* @author William Theo
* @date 7/3/2025
*/
public interface ProductRepository {
    // Get all product
    List<Product> selectAllProducts();
    // Find product by ID
    Optional<Product> selectProductById(String id);
    // Lock a product by ID
    Optional<Product> lockProductById(String id);
    // Insert a new product
    boolean addProduct(Product product);
    // Update product info
    boolean updateProduct(Product product);
    // Delete product by ID
    boolean deleteById(String id);
}
