package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementTireDTO;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.service.ProductManagementService;
import com.tracktive.productservice.service.TireService;
import com.tracktive.productservice.util.converter.Impl.ProductConverter;
import com.tracktive.productservice.util.converter.Impl.ProductManagementConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final TireService tireService;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);

    public ProductManagementServiceImpl(ProductService productService, TireService tireService) {
        this.productService = productService;
        this.tireService = tireService;
    }

    @Override
    public List<ProductManagementDTO> selectAllProducts() {
        return productService.selectAllProducts()
                .stream()
                .map(ProductConverter::toProductManagementDTO)
                .toList();
    }

    @Override
    public ProductManagementDTO selectProductById(String productId) {

        ProductDTO product = productService.selectProductById(productId);

        TireDTO tire = tireService.selectTireById(productId);

        ProductManagementTireDTO productManagementTireDTO = ProductManagementConverter.toProductManagementTireDTO(product, tire);

        return productManagementTireDTO;
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
