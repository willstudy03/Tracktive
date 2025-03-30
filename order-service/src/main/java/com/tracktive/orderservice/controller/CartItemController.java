package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.DTO.CartItemRequestDTO;
import com.tracktive.orderservice.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("retailer/{userId}")
    public ResponseEntity<List<CartItemDTO>> getAllCartItemByUserId(@PathVariable String userId){
        List<CartItemDTO> cartItems = cartItemService.selectAllByRetailerId(userId);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable String cartItemId){
        CartItemDTO cartItem = cartItemService.selectCartItemById(cartItemId);
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addItemToCart(@RequestBody @Valid CartItemRequestDTO cartItemRequestDTO){
        CartItemDTO cartItem = cartItemService.addCartItem(cartItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @PutMapping
    public ResponseEntity<CartItemDTO> updateCartItem(@RequestBody @Valid CartItemDTO cartItemDTO){
        CartItemDTO cartItem = cartItemService.updateCartItem(cartItemDTO);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable String cartItemId){
        cartItemService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok("Cart Item with ID " + cartItemId + " deleted successfully.");
    }
}
