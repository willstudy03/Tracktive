package com.tracktive.orderservice.model.Enum;

/**
* Description: Order Status
* @author William Theo
* @date 15/3/2025
*/
public enum OrderStatus {

    // Order record has been created, but no action taken yet
    INIT,

    // Order is created but wait for stock confirmation
    // OR awaiting approval for pay-by-terms
    PENDING,

    // Order is created and ready for payment
    READY_FOR_PAYMENT,

    // Payment confirmed (or terms accepted), order successfully placed with the seller
    PLACED,

    // Order is fully delivered AND fully paid
    COMPLETED,

    // Order was canceled (either before processing OR after a failed payment)
    CANCELLED;
}

