package com.tracktive.paymentservice.controller;

import com.tracktive.paymentservice.model.DTO.CheckOutRequestDTO;
import com.tracktive.paymentservice.model.DTO.CheckOutResponseDTO;
import com.tracktive.paymentservice.service.CheckOutService;
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
@RequestMapping("/check-out")
public class CheckOutController {

    private final CheckOutService checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping
    public ResponseEntity<CheckOutResponseDTO> makePayment(@RequestBody CheckOutRequestDTO checkOutRequestDTO){
        CheckOutResponseDTO checkOutResponseDTO = checkOutService.initiatePayment(checkOutRequestDTO);
        return ResponseEntity.ok(checkOutResponseDTO);
    }
}
