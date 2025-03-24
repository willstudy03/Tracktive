package com.tracktive.paymentservice.exception;

public class PaymentTransactionAlreadyExistException extends RuntimeException {
    public PaymentTransactionAlreadyExistException(String message) {
        super(message);
    }

    public PaymentTransactionAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
