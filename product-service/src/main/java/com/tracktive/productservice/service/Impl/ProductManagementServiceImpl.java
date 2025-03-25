package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.entity.Product;
import com.tracktive.productservice.service.ProductManagementService;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.util.factory.ProductManagementConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Description: Product Management Service Implementation
* @author William Theo
* @date 25/3/2025
*/
@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductService productService;

    private final ProductManagementConverterFactory productManagementConverterFactory;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);

    @Autowired
    public ProductManagementServiceImpl(ProductService productService, ProductManagementConverterFactory productManagementConverterFactory) {
        this.productService = productService;
        this.productManagementConverterFactory = productManagementConverterFactory;
    }

    @Override
    public List<ProductManagementDTO> selectAllProducts() {
        return List.of();
    }

    @Override
    public ProductManagementDTO selectProductById(String productId) {
        ProductDTO product = productService.selectProductById(productId);
        return productManagementConverterFactory.convertProduct(product);
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
