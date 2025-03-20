package com.tracktive.orderservice.exception;

/**
* Description: Custom Exception for Order Item Not Found
* @author William Theo
* @date 20/3/2025
*/
public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
