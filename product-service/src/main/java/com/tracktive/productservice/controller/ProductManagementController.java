package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: Product Management API Entry Point
* @author William Theo
* @date 25/3/2025
*/
@RestController
@RequestMapping("api/admin/products")
public class ProductManagementController {

    private final ProductManagementService productManagementService;

    @Autowired
    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductManagementDTO> getProductById(@PathVariable String productId){
        ProductManagementDTO product = productManagementService.selectProductById(productId);
        return ResponseEntity.ok().body(product);
    }
}
