package com.tracktive.orderservice.exception;

/**
 * Description: Custom exception for order item already exist
 * @author William Theo
 * @date 19/3/2025
 */
public class OrderItemAlreadyExistException extends RuntimeException{
    public OrderItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
