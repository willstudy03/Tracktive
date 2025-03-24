package com.tracktive.paymentservice.exception;

/**
* Description: Custom Exception for Payment Already Exist
* @author William Theo
* @date 24/3/2025
*/
public class PaymentAlreadyExistException extends RuntimeException {

  public PaymentAlreadyExistException(String message) {
        super(message);
    }

  public PaymentAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
