package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.OrderPlacementRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderPlacementResponseDTO;
import com.tracktive.orderservice.service.OrderPlacementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-placement")
public class OrderPlacementController {

    private final OrderPlacementService orderPlacementService;

    @Autowired
    public OrderPlacementController(OrderPlacementService orderPlacementService) {
        this.orderPlacementService = orderPlacementService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderPlacementResponseDTO> addItemToCart(@RequestBody @Valid OrderPlacementRequestDTO orderPlacementRequestDTO){
        OrderPlacementResponseDTO orderPlacement = orderPlacementService.placeOrder(orderPlacementRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPlacement);
    }
}
