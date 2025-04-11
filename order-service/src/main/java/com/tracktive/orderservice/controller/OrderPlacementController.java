package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.OrderActionRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderActionResponseDTO;
import com.tracktive.orderservice.service.OrderActionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/placeOrder")
public class OrderPlacementController {

    private final OrderActionService orderActionService;

    @Autowired
    public OrderPlacementController(OrderActionService orderActionService) {
        this.orderActionService = orderActionService;
    }

    @PostMapping
    public ResponseEntity<OrderActionResponseDTO> addItemToCart(@RequestBody @Valid OrderActionRequestDTO orderActionRequestDTO){
        OrderActionResponseDTO orderPlacement = orderActionService.placeOrder(orderActionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPlacement);
    }
}
