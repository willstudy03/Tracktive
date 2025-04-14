package com.tracktive.paymentservice.exception;
/**
* Description: Custom Exception for Invalid Payment Status
* @author William Theo
* @date 14/4/2025
*/
public class InvalidPaymentStatusException extends RuntimeException {
    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
