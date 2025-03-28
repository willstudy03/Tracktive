package com.tracktive.productservice.util.converter;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;

/**
* Description: Util for convert Product Management Model
* @author William Theo
* @date 28/3/2025
*/
public interface ProductManagementConverter<R extends ProductManagementDTO>{
    R read(ProductDTO productDTO);
    R create(ProductManagementRequestDTO productManagementRequestDTO);
    R update(ProductManagementDTO productManagementDTO);
    R delete(String id);
}
