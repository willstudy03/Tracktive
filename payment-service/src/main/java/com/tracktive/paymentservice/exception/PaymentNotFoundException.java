package com.tracktive.paymentservice.exception;

/**
* Description: Custom Exception for Payment Not Found
* @author William Theo
* @date 24/3/2025
*/
public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
