package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.DTO.OrderItemRequestDTO;
import com.tracktive.orderservice.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItemsByOrder(@PathVariable String orderId){
        List<OrderItemDTO> orderItems = orderItemService.selectAllOrderItems()
                .stream()
                .filter(orderItem -> orderItem.getOrderId().equals(orderId))
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable String orderItemId){
        OrderItemDTO orderItem = orderItemService.selectOrderItemById(orderItemId);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> addItemToCart(@RequestBody @Valid OrderItemRequestDTO orderItemRequestDTO){
        OrderItemDTO orderItem = orderItemService.addOrderItem(orderItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }

    @PutMapping
    public ResponseEntity<OrderItemDTO> updateCartItem(@RequestBody @Valid OrderItemDTO orderItemDTO){
        OrderItemDTO orderItem = orderItemService.updateOrderItem(orderItemDTO);
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable String orderItemId){
        orderItemService.deleteOrderItemById(orderItemId);
        return ResponseEntity.ok("Order Item with ID " + orderItemId + " deleted successfully.");
    }
}
