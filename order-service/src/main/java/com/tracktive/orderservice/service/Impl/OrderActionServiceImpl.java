package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.EmptyCartException;
import com.tracktive.orderservice.kafka.OrderEventProducer;
import com.tracktive.orderservice.model.DTO.*;
import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.service.CartItemService;
import com.tracktive.orderservice.service.OrderActionService;
import com.tracktive.orderservice.service.OrderItemService;
import com.tracktive.orderservice.service.OrderService;
import com.tracktive.orderservice.util.PriceCalculatorUtil;
import com.tracktive.orderservice.util.converter.OrderConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Description: Order Action Service Implementation
* @author William Theo
* @date 6/4/2025
*/
@Service
public class OrderActionServiceImpl implements OrderActionService {

    private final CartItemService cartItemService;

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final OrderEventProducer orderEventProducer;

    @Autowired
    public OrderActionServiceImpl(CartItemService cartItemService, OrderService orderService, OrderItemService orderItemService, OrderEventProducer orderEventProducer) {
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.orderEventProducer = orderEventProducer;
    }

    @Override
    @Transactional
    public OrderActionResponseDTO placeOrder(OrderActionRequestDTO orderActionRequestDTO) {

        // Select All Retailer Cart Item
        List<CartItemDTO> cartItems = cartItemService.selectAllByRetailerId(orderActionRequestDTO.getRetailerId());

        // If there is no cart items, failed to place order
        if (cartItems.isEmpty()){
            throw new EmptyCartException("Order cannot be placed because the cart is empty. Add items to proceed.");
        }

        // Building the resquest
        OrderRequestDTO orderRequestDTO = OrderConverter.toOrderRequestDTO(orderActionRequestDTO, cartItems);

        OrderDTO newOrder = orderService.addOrder(orderRequestDTO);

        // Convert CartItem into OrderItem
        List<OrderItemDTO> orderItems = cartItems.stream()
                .map(cartItemDTO -> {
                        OrderItemRequestDTO orderItemRequestDTO = new OrderItemRequestDTO();
                        orderItemRequestDTO.setOrderId(newOrder.getId());
                        BeanUtils.copyProperties(cartItemDTO, orderItemRequestDTO);
                        return orderItemService.addOrderItem(orderItemRequestDTO);})
                .toList();

        // Send stock deduction event to notify inventory service to deduct stock
        orderEventProducer.sendStockDeductionEvent(newOrder.getId(), orderItems);

        return new OrderActionResponseDTO(newOrder.getId());
    }
}
