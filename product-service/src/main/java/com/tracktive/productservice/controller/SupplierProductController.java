package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductRequestDTO;
import com.tracktive.productservice.service.SupplierProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Supplier Product API Entry Point
* @author William Theo
* @date 28/3/2025
*/
@RestController
@RequestMapping("api/supplier/products")
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    @GetMapping("supplier/{userId}")
    public ResponseEntity<List<SupplierProductDTO>> getSupplierProducts(@PathVariable String userId){
        List<SupplierProductDTO> products = supplierProductService.selectSupplierProductsBySupplierId(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SupplierProductDTO> getSupplierProduct(@PathVariable String productId){
        SupplierProductDTO product = supplierProductService.selectSupplierProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<SupplierProductDTO> createSupplierProduct(@Valid @RequestBody SupplierProductRequestDTO supplierProductRequestDTO){
        SupplierProductDTO product = supplierProductService.addSupplierProduct(supplierProductRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<SupplierProductDTO> updateSupplierProduct(@Valid @RequestBody SupplierProductDTO supplierProductDTO){
        SupplierProductDTO product = supplierProductService.updateSupplierProduct(supplierProductDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteSupplierProduct(@PathVariable String productId){
        supplierProductService.deleteSupplierProductById(productId);
        return ResponseEntity.ok("Product with ID " + productId + " deleted successfully.");
    }
}
