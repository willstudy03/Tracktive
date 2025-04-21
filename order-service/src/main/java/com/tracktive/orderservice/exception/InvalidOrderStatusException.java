package com.tracktive.orderservice.exception;

/**
* Description: Custom Exception for Invalid Order Status
* @author William Theo
* @date 21/4/2025
*/
public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
