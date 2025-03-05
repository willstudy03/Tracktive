package com.tracktive.userservice.exception;

/**
 * Description: Custom exception for failed to acquire lock
 * @author William Theo
 * @date 5/3/2025
 */
public class LockAcquisitionException extends RuntimeException {
    public LockAcquisitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
