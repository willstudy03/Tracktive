package com.tracktive.paymentservice.controller;

import com.tracktive.paymentservice.model.DTO.PaymentProcessorRequestDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessorResponseDTO;
import com.tracktive.paymentservice.service.PaymentProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description:
* @author William Theo
* @date 15/4/2025
*/
@RestController
@RequestMapping("api/payments")
public class PaymentProcessController {

    private final PaymentProcessorService paymentProcessorService;

    @Autowired
    public PaymentProcessController(PaymentProcessorService paymentProcessorService) {
        this.paymentProcessorService = paymentProcessorService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentProcessorResponseDTO> makePayment(@RequestBody PaymentProcessorRequestDTO paymentProcessorRequestDTO){
        PaymentProcessorResponseDTO paymentProcessorResponseDTO = paymentProcessorService.initiatePayment(paymentProcessorRequestDTO);
        return ResponseEntity.ok(paymentProcessorResponseDTO);
    }
}
