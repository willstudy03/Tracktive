package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.CartItemManagementRequestDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;
import com.tracktive.orderservice.service.CartItemManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Cart Item Management API End Point
* @author William Theo
* @date 4/4/2025
*/
@RestController
@RequestMapping("api/cart")
public class CartItemManagementController {

    private final CartItemManagementService cartItemManagementService;

    @Autowired
    public CartItemManagementController(CartItemManagementService cartItemManagementService) {
        this.cartItemManagementService = cartItemManagementService;
    }

    @GetMapping("/{retailerId}")
    public ResponseEntity<List<CartItemManagementResponseDTO>> getCartItem(@PathVariable String retailerId){
        List<CartItemManagementResponseDTO> cartItems = cartItemManagementService.selectCartItems(retailerId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping
    public ResponseEntity<CartItemManagementResponseDTO> addItemToCart(@RequestBody @Valid CartItemManagementRequestDTO cartItemManagementRequestDTO){
        CartItemManagementResponseDTO cartItem = cartItemManagementService.addCartItem(cartItemManagementRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }
}
