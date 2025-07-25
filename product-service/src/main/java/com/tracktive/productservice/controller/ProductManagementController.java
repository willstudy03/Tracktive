package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;
import com.tracktive.productservice.service.ProductManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Description: Product Management API Entry Point
* @author William Theo
* @date 25/3/2025
*/
@RestController
@RequestMapping("/products-management")
public class ProductManagementController {

    private final ProductManagementService productManagementService;

    @Autowired
    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductManagementDTO>> getProducts(){
        List<ProductManagementDTO> products = productManagementService.selectAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductManagementDTO> getProductById(@PathVariable String productId){
        ProductManagementDTO product = productManagementService.selectProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductManagementDTO> addProduct(@Valid @RequestPart ProductManagementRequestDTO productManagementRequestDTO, @RequestPart(required = false) MultipartFile file){
        ProductManagementDTO product = productManagementService.createProduct(productManagementRequestDTO, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductManagementDTO> updateProduct(@Valid @RequestPart ProductManagementDTO productManagementDTO, @RequestPart(required = false) MultipartFile file) {
        ProductManagementDTO product = productManagementService.updateProduct(productManagementDTO, file);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        ProductManagementDTO product = productManagementService.deleteProduct(productId);
        return ResponseEntity.ok("Product with ID " + product.getProductId() + " deleted successfully.");
    }
}
