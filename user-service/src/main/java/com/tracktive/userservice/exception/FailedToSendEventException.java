package com.tracktive.userservice.exception;

public class FailedToSendEventException extends RuntimeException {
    public FailedToSendEventException(String message) {
        super(message);
    }

    public FailedToSendEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
