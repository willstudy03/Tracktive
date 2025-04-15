package com.tracktive.paymentservice.util.converter;

import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionRequestDTO;
import com.tracktive.paymentservice.model.Enum.StripePaymentStatus;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.UUID;

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
        BeanUtils.copyProperties(paymentTransaction, dto);
        return dto;
    }

    public static PaymentTransactionDTO toDTO(PaymentTransactionRequestDTO paymentTransactionRequestDTO){
        if (Objects.isNull(paymentTransactionRequestDTO)) {
            return null;
        }
        PaymentTransactionDTO dto = new PaymentTransactionDTO();
        dto.setId(UUID.randomUUID().toString());
        dto.setStripePaymentStatus(StripePaymentStatus.PENDING);
        BeanUtils.copyProperties(paymentTransactionRequestDTO, dto);
        return dto;
    }


    public static PaymentTransaction toEntity(PaymentTransactionDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        BeanUtils.copyProperties(dto, paymentTransaction);
        return paymentTransaction;
    }

    public static PaymentTransactionRequestDTO toRequest(PaymentDTO paymentDTO, String stripeSessionID){
        if (Objects.isNull(paymentDTO)) {
            return null;
        }
        PaymentTransactionRequestDTO requestDTO = new PaymentTransactionRequestDTO();
        requestDTO.setPaymentId(paymentDTO.getId());
        requestDTO.setStripeSessionId(stripeSessionID);
        requestDTO.setAmount(paymentDTO.getAmount());
        requestDTO.setCurrency(paymentDTO.getCurrency());
        return requestDTO;
    }
}
