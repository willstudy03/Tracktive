package com.tracktive.paymentservice.util;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;

import java.util.Objects;

/**
* Description: Util for convert Payment Transaction Model
* @author William Theo
* @date 24/3/2025
*/
public class PaymentTransactionConverter {
    // Private constructor to prevent instantiation
    private PaymentTransactionConverter() {
    }

    public static PaymentTransactionDTO toDTO(PaymentTransaction paymentTransaction) {
        if (Objects.isNull(paymentTransaction)) {
            return null;
        }
        PaymentTransactionDTO dto = new PaymentTransactionDTO();
        dto.setId(paymentTransaction.getId());
        dto.setPaymentId(paymentTransaction.getPaymentId());
        dto.setStripePaymentIntentId(paymentTransaction.getStripePaymentIntentId());
        dto.setStripeChargeId(paymentTransaction.getStripeChargeId());
        dto.setStripePaymentStatus(paymentTransaction.getStripePaymentStatus());
        dto.setReceiptUrl(paymentTransaction.getReceiptUrl());
        dto.setAmount(paymentTransaction.getAmount());
        dto.setCurrency(paymentTransaction.getCurrency());
        dto.setStripeCreatedAt(paymentTransaction.getStripeCreatedAt());
        dto.setUpdatedAt(paymentTransaction.getUpdatedAt());
        dto.setCreatedAt(paymentTransaction.getCreatedAt());
        return dto;
    }

    public static PaymentTransaction toEntity(PaymentTransactionDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId(dto.getId());
        paymentTransaction.setPaymentId(dto.getPaymentId());
        paymentTransaction.setStripePaymentIntentId(dto.getStripePaymentIntentId());
        paymentTransaction.setStripeChargeId(dto.getStripeChargeId());
        paymentTransaction.setStripePaymentStatus(dto.getStripePaymentStatus());
        paymentTransaction.setReceiptUrl(dto.getReceiptUrl());
        paymentTransaction.setAmount(dto.getAmount());
        paymentTransaction.setCurrency(dto.getCurrency());
        paymentTransaction.setStripeCreatedAt(dto.getStripeCreatedAt());
        paymentTransaction.setUpdatedAt(dto.getUpdatedAt());
        paymentTransaction.setCreatedAt(dto.getCreatedAt());
        return paymentTransaction;
    }
}
