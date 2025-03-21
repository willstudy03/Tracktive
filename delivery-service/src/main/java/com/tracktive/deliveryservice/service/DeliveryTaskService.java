package com.tracktive.deliveryservice.service;

import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;

import java.util.List;
/**
* Description: Delivery Task Service Interface
* @author William Theo
* @date 21/3/2025
*/
public interface DeliveryTaskService {
    // Select operations
    List<DeliveryTaskDTO> selectAllDeliveryTasks();

    List<DeliveryTaskDTO> selectAllDeliveryTasksByCourierId(String id);

    DeliveryTaskDTO selectDeliveryTaskByOrderId(String id);

    DeliveryTaskDTO selectDeliveryTaskById(String id);

    // Lock operation
    DeliveryTaskDTO lockDeliveryTaskById(String id);

    // Insert operation
    void addDeliveryTask(DeliveryTaskDTO deliveryTaskDTO);

    // Update operation
    void updateDeliveryTask(DeliveryTaskDTO deliveryTaskDTO);

    // Delete operation
    void deleteDeliveryTaskById(String id);
}
