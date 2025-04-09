package com.tracktive.orderservice.exception;
/**
* Description: Util for failed to place an order exception
* @author William Theo
* @date 9/4/2025
*/
public class FailedToPlaceOrderException extends RuntimeException {
    public FailedToPlaceOrderException(String message) {
        super(message);
    }
}
