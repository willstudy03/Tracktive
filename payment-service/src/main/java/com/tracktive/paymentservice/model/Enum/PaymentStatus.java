package com.tracktive.paymentservice.model.Enum;

public enum PaymentStatus {
    PENDING,         // Payment initiated but not completed yet
    PROCESSING,      // Payment is being processed by Stripe
    COMPLETED,       // Payment was successfully made
    FAILED,          // Payment failed (e.g., declined, expired)
    CANCELED,        // Payment was canceled by the user or system
    REFUNDED,        // Payment was refunded
    PARTIALLY_PAID,  // For PAY_BY_TERM: some installments are paid
    FULLY_PAID,      // For PAY_BY_TERM: all installments are paid
    OVERDUE          // For PAY_BY_TERM: missed a scheduled payment
}
