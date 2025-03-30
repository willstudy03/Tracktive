package com.tracktive.deliveryservice.controller;

import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.DTO.DeliveryTaskRequestDTO;
import com.tracktive.deliveryservice.service.DeliveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Description: Delivery API Entry Point
* @author William Theo
* @date 30/3/2025
*/
@RestController
@RequestMapping("api/delivery")
public class DeliveryTaskController {

    private final DeliveryTaskService deliveryTaskService;

    @Autowired
    public DeliveryTaskController(DeliveryTaskService deliveryTaskService) {
        this.deliveryTaskService = deliveryTaskService;
    }

    @GetMapping("courier/{userId}")
    public ResponseEntity<List<DeliveryTaskDTO>> getAllRetailerInventory(@PathVariable String userId){
        List<DeliveryTaskDTO> deliveryTasks = deliveryTaskService.selectAllDeliveryTasksByCourierId(userId);
        return ResponseEntity.ok(deliveryTasks);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryTaskDTO> getRetailerInventoryProduct(@PathVariable String deliveryId){
        DeliveryTaskDTO deliveryTask = deliveryTaskService.selectDeliveryTaskById(deliveryId);
        return ResponseEntity.ok(deliveryTask);
    }

    @PostMapping
    public ResponseEntity<DeliveryTaskDTO> createDeliveryTask(@RequestBody DeliveryTaskRequestDTO deliveryTaskRequestDTO){
        DeliveryTaskDTO deliveryTask = deliveryTaskService.addDeliveryTask(deliveryTaskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryTask);
    }

    @PutMapping
    public ResponseEntity<DeliveryTaskDTO> updateDeliveryTask(@RequestBody DeliveryTaskDTO deliveryTaskDTO){
        DeliveryTaskDTO deliveryTask = deliveryTaskService.updateDeliveryTask(deliveryTaskDTO);
        return ResponseEntity.ok(deliveryTask);
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<String> deliveryDeliveryTask(@PathVariable String deliveryId){
        deliveryTaskService.deleteDeliveryTaskById(deliveryId);
        return ResponseEntity.ok("Product with ID " + deliveryId + " deleted successfully.");
    }
}
