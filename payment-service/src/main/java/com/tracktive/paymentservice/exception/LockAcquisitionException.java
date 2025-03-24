package com.tracktive.paymentservice.exception;

/**
* Description: Custom Exception for failed to acquire lock
* @author William Theo
* @date 24/3/2025
*/
public class LockAcquisitionException extends RuntimeException {
    public LockAcquisitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
