package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.OrderCancellationRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderCancellationResponseDTO;
import com.tracktive.orderservice.service.OrderCancellationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: Order Cancellation Controller
* @author William Theo
* @date 21/4/2025
*/
@RestController
@RequestMapping("api/cancelOrder")
public class OrderCancellationController {

    private final OrderCancellationService orderCancellationService;

    @Autowired
    public OrderCancellationController(OrderCancellationService orderCancellationService) {
        this.orderCancellationService = orderCancellationService;
    }

    @PostMapping
    public ResponseEntity<OrderCancellationResponseDTO> cancelOrder(@RequestBody @Valid OrderCancellationRequestDTO orderCancellationRequestDTO){
        OrderCancellationResponseDTO cancelledOrder = orderCancellationService.cancelOrder(orderCancellationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cancelledOrder);
    }
}
