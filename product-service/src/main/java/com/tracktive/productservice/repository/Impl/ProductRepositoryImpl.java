package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.exception.DatabaseOperationException;
import com.tracktive.productservice.exception.ProductAlreadyExistsException;
import com.tracktive.productservice.model.DAO.ProductDAO;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.entity.Product;
import com.tracktive.productservice.repository.ProductRepository;
import com.tracktive.productservice.util.converter.Impl.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
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
        return productDAO.selectProductById(id).map(ProductConverter::toDTO);
    }

    @Override
    public Optional<ProductDTO> lockProductById(String id) {
        return productDAO.lockProductById(id).map(ProductConverter::toDTO);
    }

    @Override
    public boolean addProduct(ProductDTO productDTO) {
        try {
            Product product = ProductConverter.toEntity(productDTO);
            return productDAO.addProduct(product) > 0;
        } catch (DuplicateKeyException e) {
            throw new ProductAlreadyExistsException("Product with id " + productDTO.getProductId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add product to the database", e);
        }
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) {
        Product product = ProductConverter.toEntity(productDTO);
        return productDAO.updateProduct(product) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return productDAO.deleteProductById(id) > 0;
    }

}
