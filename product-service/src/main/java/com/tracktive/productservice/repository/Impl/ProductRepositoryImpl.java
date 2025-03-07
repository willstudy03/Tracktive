package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.ProductDAO;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.entity.Product;
import com.tracktive.productservice.repository.ProductRepository;
import com.tracktive.productservice.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProductDTO> selectAllProducts() {
        return Optional.ofNullable(productDAO.selectAllProducts())
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> selectProductById(String id) {
        validateId(id);
        return productDAO.selectProductById(id).map(ProductConverter::toDTO);
    }

    @Override
    public Optional<ProductDTO> lockProductById(String id) {
        validateId(id);
        return productDAO.lockProductById(id).map(ProductConverter::toDTO);
    }

    @Override
    public boolean addProduct(ProductDTO productDTO) {
        validateProductDTO(productDTO);
        Product product = ProductConverter.toEntity(productDTO);
        return productDAO.addProduct(product) > 0;
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) {
        validateProductDTO(productDTO);
        Product product = ProductConverter.toEntity(productDTO);
        return productDAO.updateProduct(product) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return productDAO.deleteProductById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
    }

    private void validateProductDTO(ProductDTO productDTO) {
        if (Objects.isNull(productDTO)) {
            throw new IllegalArgumentException("ProductDTO cannot be null");
        }
    }
}
