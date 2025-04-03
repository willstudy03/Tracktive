package com.tracktive.orderservice.exception;
/**
* Description: Custom Exception for Cart Supplier Mismatch
* @author William Theo
* @date 4/4/2025
*/
public class CartSupplierMismatchException extends RuntimeException {
    public CartSupplierMismatchException(String message) {
        super(message);
    }
}
