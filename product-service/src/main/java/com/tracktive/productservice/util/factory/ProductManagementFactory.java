package com.tracktive.productservice.util.factory;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;

/**
* Description: Product Management Factory Interface
* @author William Theo
* @date 28/3/2025
*/
public interface ProductManagementFactory {
    ProductManagementDTO selectProduct(ProductDTO productDTO);
    ProductManagementDTO addProduct(ProductManagementRequestDTO productManagementRequestDTO);
    ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO);
    ProductManagementDTO deleteProduct(ProductDTO productDTO);
}
