package com.tracktive.paymentservice.exception;

/**
* Description: Custom Exception for Payment Transaction Not Found
* @author William Theo
* @date 24/3/2025
*/
public class PaymentTransactionNotFoundException extends RuntimeException {
    public PaymentTransactionNotFoundException(String message) {
        super(message);
    }
}
