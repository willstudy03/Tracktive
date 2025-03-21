package com.tracktive.deliveryservice.model.Enum;

public enum DeliveryStatus {
    PENDING,          // Delivery task created but not yet assigned
    ASSIGNED,         // Courier assigned but not yet picked up and deliver
    IN_TRANSIT,       // Package is pick up and on the way to the destination
    DELIVERED,        // Successfully delivered
    FAILED,           // Delivery failed (e.g., recipient not available)
    CANCELLED         // Delivery task cancelled
}
