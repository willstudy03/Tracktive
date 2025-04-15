package com.tracktive.paymentservice.model.Enum;
/**
* Description: Stripe Payment Status
* @author William Theo
* @date 15/4/2025
*/
public enum StripePaymentStatus {
    PENDING,        // Initial state, session created
    SUCCEEDED,      // Payment completed successfully
    PROCESSING,     // Payment is being processed
    REQUIRES_ACTION, // Requires customer action (like 3D Secure verification)
    REQUIRES_PAYMENT_METHOD, // Initial payment method failed
    REQUIRES_CONFIRMATION, // Requires confirmation
    CANCELED,       // Payment was canceled
    EXPIRED,        // Session expired
    FAILED          // Payment attempt failed
}
