package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.InvalidOrderStatusException;
import com.tracktive.orderservice.kafka.OrderEventProducer;
import com.tracktive.orderservice.model.DTO.OrderCancellationRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderCancellationResponseDTO;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.service.OrderCancellationService;
import com.tracktive.orderservice.service.OrderItemService;
import com.tracktive.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* Description: Order Cancellation Service Implementation
* @author William Theo
* @date 21/4/2025
*/
@Service
public class OrderCancellationServiceImpl implements OrderCancellationService {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final OrderEventProducer orderEventProducer;

    private static final Logger log = LoggerFactory.getLogger(OrderCancellationServiceImpl.class);

    @Autowired
    public OrderCancellationServiceImpl(OrderService orderService, OrderItemService orderItemService, OrderEventProducer orderEventProducer) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.orderEventProducer = orderEventProducer;
    }

    @Override
    @Transactional
    public OrderCancellationResponseDTO cancelOrder(OrderCancellationRequestDTO orderCancellationRequestDTO) {

        log.info("OrderCancellationService: Received order cancellation request for order id {}", orderCancellationRequestDTO.getOrderId());

        // Lock the order for cancellation
        OrderDTO orderDTO = orderService.lockOrderById(orderCancellationRequestDTO.getOrderId());

        // Only Order with READY_FOR_PAYMENT status can be cancelled (Order is created, stock has been reserved, wait for payment)
        if (!orderDTO.getOrderStatus().equals(OrderStatus.READY_FOR_PAYMENT)) {
            throw new InvalidOrderStatusException("Order Cancellation: Invalid Order Status with Order ID{" + orderCancellationRequestDTO.getOrderId() +"}");
        }

        List<OrderItemDTO> orderItems = orderItemService.selectAllOrderItems()
                .stream()
                .filter(orderItemDTO -> orderItemDTO.getOrderId().equals(orderDTO.getId()))
                .collect(Collectors.toList());

        //@TODO: Transaction Problem with Kafka message
        // Publish event to product service to restore back the deducted stock
        orderEventProducer.sendStockRestockEvent(orderDTO.getId(), orderItems);

        // Publish event to payment service to set payment status into cancel
        orderEventProducer.sendPaymentCancellationRequest(orderDTO);

        // Set the order status into CANCELLED
        orderDTO.setOrderStatus(OrderStatus.CANCELLED);

        // Persistence the changes into the DB
        OrderDTO cancelledOrder = orderService.updateOrder(orderDTO);

        return new OrderCancellationResponseDTO(cancelledOrder.getId());
    }
}
