package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Description: Product Management Service Interface
* @author William Theo
* @date 25/3/2025
*/
public interface ProductManagementService {

    List<ProductManagementDTO> selectAllProducts();

    ProductManagementDTO selectProductById(String productId);

    ProductManagementDTO createProduct(ProductManagementRequestDTO productManagementRequestDTO, MultipartFile file);

    ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO, MultipartFile file);

    ProductManagementDTO deleteProduct(String productId);
}
