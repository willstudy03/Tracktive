package com.tracktive.paymentservice.controller;

import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;
import com.tracktive.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Payment API End Point
* @author William Theo
* @date 30/3/2025
*/
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<PaymentDTO>> getAllPaymentByUserId(@PathVariable String userId){
        List<PaymentDTO> payments = paymentService.selectAllPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable String paymentId){
        PaymentDTO payment = paymentService.selectPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO){
        PaymentDTO payment = paymentService.addPayment(paymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PutMapping
    public ResponseEntity<PaymentDTO> updatePayment(@Valid @RequestBody PaymentDTO paymentDTO){
        PaymentDTO payment = paymentService.updatePayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePaymentByOrderId(@PathVariable String paymentId){
        paymentService.deletePaymentById(paymentId);
        return ResponseEntity.ok("Payment with ID " + paymentId + " deleted successfully.");
    }
}
