package com.tracktive.productservice.util.converter;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;

/**
* Description: Generic Interface for converting products into the correct DTO dynamically
* @author William Theo
* @date 25/3/2025
*/
public interface ProductManagementConverter<T extends ProductManagementDTO> {
    T read(ProductDTO productDTO);
    T create(ProductManagementDTO productManagementDTO);
}
