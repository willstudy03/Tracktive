package com.tracktive.productservice.exception;

public class FailedToUploadImageException extends RuntimeException {
    public FailedToUploadImageException(String message) {
        super(message);
    }

    public FailedToUploadImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
