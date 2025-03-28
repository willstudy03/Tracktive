package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;
import com.tracktive.productservice.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProductManagementDTO>> getProducts(){
        List<ProductManagementDTO> products = productManagementService.selectAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductManagementDTO> getProductById(@PathVariable String productId){
        ProductManagementDTO product = productManagementService.selectProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductManagementDTO> addProduct(@RequestBody ProductManagementRequestDTO productManagementRequestDTO){
        ProductManagementDTO product = productManagementService.createProduct(productManagementRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<ProductManagementDTO> updateProduct(@RequestBody ProductManagementDTO productManagementDTO) {
        ProductManagementDTO product = productManagementService.updateProduct(productManagementDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        ProductManagementDTO product = productManagementService.deleteProduct(productId);
        return ResponseEntity.ok("Product with ID " + product.getProductId() + " deleted successfully.");
    }
}
