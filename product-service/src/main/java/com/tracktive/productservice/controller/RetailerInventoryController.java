package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.DTO.RetailerInventoryRequestDTO;
import com.tracktive.productservice.service.RetailerInventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Retailer Inventory API Entry Point
* @author William Theo
* @date 29/3/2025
*/
@RestController
@RequestMapping("/retailers")
public class RetailerInventoryController {

    private final RetailerInventoryService retailerInventoryService;

    @Autowired
    public RetailerInventoryController(RetailerInventoryService retailerInventoryService) {
        this.retailerInventoryService = retailerInventoryService;
    }

    @GetMapping("/{userId}/inventory")
    public ResponseEntity<List<RetailerInventoryDTO>> getAllRetailerInventory(@PathVariable String userId){
        List<RetailerInventoryDTO> products = retailerInventoryService.selectRetailerInventoryByRetailerId(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{userId}/inventory/{productId}")
    public ResponseEntity<RetailerInventoryDTO> getRetailerInventoryProduct(@PathVariable String productId){
        RetailerInventoryDTO product = retailerInventoryService.selectRetailerInventoryById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/{userId}/inventory")
    public ResponseEntity<RetailerInventoryDTO> createRetailerInventoryProduct(@Valid @RequestBody RetailerInventoryRequestDTO retailerInventoryRequestDTO){
        RetailerInventoryDTO product = retailerInventoryService.addRetailerInventory(retailerInventoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{userId}/inventory")
    public ResponseEntity<RetailerInventoryDTO> updateRetailerInventoryProduct(@Valid @RequestBody RetailerInventoryDTO retailerInventoryDTO){
        RetailerInventoryDTO product = retailerInventoryService.updateRetailerInventory(retailerInventoryDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{userId}/inventory/{productId}")
    public ResponseEntity<String> deleteRetailerProduct(@PathVariable String productId){
        retailerInventoryService.deleteRetailerInventoryById(productId);
        return ResponseEntity.ok("Product with ID " + productId + " deleted successfully.");
    }
}
