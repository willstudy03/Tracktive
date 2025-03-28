package com.tracktive.productservice.util.converter;

import com.tracktive.productservice.model.DTO.*;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.service.TireService;
import com.tracktive.productservice.util.converter.Impl.TireSKUConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
* Description: Product Management Tire Converter
* @author William Theo
* @date 28/3/2025
*/
@Component
public class ProductManagementTireConverter implements ProductManagementConverter<ProductManagementTireDTO> {

    private final ProductService productService;

    private final TireService tireService;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementTireConverter.class);


    @Autowired
    public ProductManagementTireConverter(ProductService productService, TireService tireService) {
        this.productService = productService;
        this.tireService = tireService;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductManagementTireDTO read(ProductDTO productDTO) {
        ProductManagementTireDTO productManagementTireDTO = new ProductManagementTireDTO();
        BeanUtils.copyProperties(productDTO, productManagementTireDTO);

        TireDTO tireDTO = tireService.selectTireById(productDTO.getProductId());
        if (!Objects.isNull(tireDTO)){
            BeanUtils.copyProperties(tireDTO, productManagementTireDTO);
        }
        return productManagementTireDTO;
    }

    @Override
    @Transactional
    public ProductManagementTireDTO create(ProductManagementRequestDTO productManagementRequestDTO) {

        ProductRequestDTO createProductRequest = new ProductRequestDTO();

        BeanUtils.copyProperties(productManagementRequestDTO, createProductRequest);

        ProductDTO product = productService.addProduct(createProductRequest);

        TireRequestDTO createTireRequest = new TireRequestDTO();

        BeanUtils.copyProperties(productManagementRequestDTO, createTireRequest);

        createTireRequest.setId(product.getProductId());

        createTireRequest.setTireSku(TireSKUConverter.generateSKU((ProductManagementRequestTireDTO) productManagementRequestDTO));

        TireDTO tire = tireService.addTire(createTireRequest);

        ProductManagementTireDTO result = new ProductManagementTireDTO();

        BeanUtils.copyProperties(product, result);

        BeanUtils.copyProperties(tire, result);

        return result;
    }

    @Override
    @Transactional
    public ProductManagementTireDTO update(ProductManagementDTO productManagementDTO) {
        return null;
    }

    @Override
    @Transactional
    public ProductManagementTireDTO delete(String id) {
        return null;
    }
}
