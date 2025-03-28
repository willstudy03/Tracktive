package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.service.SupplierProductService;
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

    @GetMapping
    public ResponseEntity<List<SupplierProductDTO>> getSupplierProducts(String userId){
        List<SupplierProductDTO> products = supplierProductService.selectSupplierProductsBySupplierId(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SupplierProductDTO> getSupplierProduct(String productId){
        SupplierProductDTO product = supplierProductService.selectSupplierProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<SupplierProductDTO> updateSupplierProduct(SupplierProductDTO supplierProductDTO){
        SupplierProductDTO product = supplierProductService.updateSupplierProduct(supplierProductDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteSupplierProduct(String productId){
        supplierProductService.deleteSupplierProductById(productId);
        return ResponseEntity.ok("Product with ID " + productId + " deleted successfully.");
    }
}
