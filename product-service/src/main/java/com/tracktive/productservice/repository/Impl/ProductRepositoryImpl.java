package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.ProductDAO;
import com.tracktive.productservice.model.entity.Product;
import com.tracktive.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
* Description: Product Repository Implementation
* @author William Theo
* @date 7/3/2025
*/
@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final ProductDAO productDAO;

    @Autowired
    public ProductRepositoryImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> selectAllProducts() {
        return Optional.ofNullable(productDAO.selectAllProducts()).orElse(Collections.emptyList());
    }

    @Override
    public Optional<Product> selectProductById(String id) {
        validateId(id);
        return productDAO.selectProductById(id);
    }

    @Override
    public Optional<Product> lockProductById(String id) {
        validateId(id);
        return productDAO.lockProductById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        validateProductDTO(product);
        return productDAO.addProduct(product) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        validateProductDTO(product);
        return productDAO.updateProduct(product) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return productDAO.deleteProductById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateProductDTO(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("productDTO cannot be null");
        }
    }
}
