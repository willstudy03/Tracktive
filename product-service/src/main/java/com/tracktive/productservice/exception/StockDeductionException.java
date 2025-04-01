package com.tracktive.productservice.exception;

/**
* Description: Custom Exception for Failed to deduct stock
* @author William Theo
* @date 1/4/2025
*/
public class StockDeductionException extends RuntimeException {
  public StockDeductionException(String message) {
    super(message);
  }
}
