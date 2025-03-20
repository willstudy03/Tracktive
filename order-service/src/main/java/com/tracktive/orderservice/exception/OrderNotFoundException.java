package com.tracktive.orderservice.exception;

/**
* Description: Custom Exception for Order Not Found
* @author William Theo
* @date 20/3/2025
*/
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
