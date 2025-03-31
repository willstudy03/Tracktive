package com.tracktive.orderservice.controller;

import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.DTO.OrderRequestDTO;
import com.tracktive.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Order Controller
* @author William Theo
* @date 31/3/2025
*/
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("retailer/{retailerId}")
    public ResponseEntity<List<OrderDTO>> getAllOrderByRetailer(@PathVariable String retailerId){
        List<OrderDTO> orders = orderService.selectAllOrdersByRetailerId(retailerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("supplier/{supplierId}")
    public ResponseEntity<List<OrderDTO>> getAllOrderBySupplier(@PathVariable String supplierId){
        List<OrderDTO> orders = orderService.selectAllOrdersBySupplierId(supplierId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId){
        OrderDTO order = orderService.selectOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO){
        OrderDTO order = orderService.addOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody @Valid OrderDTO orderDTO){
        OrderDTO order = orderService.updateOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable String orderId){
       orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " deleted successfully.");
    }
}
