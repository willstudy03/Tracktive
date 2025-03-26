package com.tracktive.productservice.controller;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductRequestDTO;
import com.tracktive.productservice.model.VO.ProductVO;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.util.converter.Impl.ProductConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* Description: Products API Entry Point
* @author William Theo
* @date 25/3/2025
*/
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductVO>> getProducts(){
        List<ProductVO> products = productService.selectAllProducts()
                .stream()
                .map(ProductConverter::toVO)
                .toList();

        return ResponseEntity.ok().body(products);
    }
}
