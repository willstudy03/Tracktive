package com.tracktive.productservice.util.factory;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;

/**
* Description: Product Management Converter Factory Interface
* @author William Theo
* @date 25/3/2025
*/
public interface ProductManagementConverterFactory {
    ProductManagementDTO readProduct(ProductDTO productDTO);
    ProductManagementDTO createProduct(ProductManagementDTO productManagementDTO);
}
