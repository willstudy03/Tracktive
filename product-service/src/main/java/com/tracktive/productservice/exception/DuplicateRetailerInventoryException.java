package com.tracktive.productservice.exception;

public class DuplicateRetailerInventoryException extends RuntimeException {
    public DuplicateRetailerInventoryException(String message) {
        super(message);
    }
}
