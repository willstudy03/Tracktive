package com.tracktive.paymentservice.util.converter;

import OrderAction.events.PaymentRequestEvent;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.model.entity.Payment;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Payment model
* @author William Theo
* @date 24/3/2025
*/
public class PaymentConverter {

    // Private constructor to prevent instantiation
    private PaymentConverter() {
    }

    public static PaymentDTO toDTO(Payment payment) {
        if (Objects.isNull(payment)) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setUserId(payment.getUserId());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setTotalPaidAmount(payment.getTotalPaidAmount());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentDTO.setUpdatedAt(payment.getUpdatedAt());
        paymentDTO.setCreatedAt(payment.getCreatedAt());
        return paymentDTO;
    }

    public static PaymentDTO toDTO(PaymentRequestDTO paymentRequestDTO){
        if (Objects.isNull(paymentRequestDTO)) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(paymentRequestDTO, paymentDTO);
        return paymentDTO;
    }

    public static PaymentRequestDTO toPaymentRequestDTO(PaymentRequestEvent event){
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setOrderId(event.getOrderId());
        dto.setUserId(event.getUserId());
        dto.setAmount(new BigDecimal(event.getAmount()));
        dto.setTotalPaidAmount(BigDecimal.ZERO); // Using BigDecimal.ZERO instead of new BigDecimal(0)
        dto.setPaymentStatus(PaymentStatus.PENDING);
        dto.setCurrency("MYR");
        return dto;
    }

    public static Payment toEntity(PaymentDTO paymentDTO) {
        if (Objects.isNull(paymentDTO)) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setUserId(paymentDTO.getUserId());
        payment.setCurrency(paymentDTO.getCurrency());
        payment.setAmount(paymentDTO.getAmount());
        payment.setTotalPaidAmount(paymentDTO.getTotalPaidAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
        payment.setUpdatedAt(paymentDTO.getUpdatedAt());
        payment.setCreatedAt(paymentDTO.getCreatedAt());
        return payment;
    }
}
