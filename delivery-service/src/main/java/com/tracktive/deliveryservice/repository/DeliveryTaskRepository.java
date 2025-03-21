package com.tracktive.deliveryservice.repository;

import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.entity.DeliveryTask;

import java.util.List;
import java.util.Optional;

/**
* Description: Delivery Task Repository Interface
* @author William Theo
* @date 21/3/2025
*/
public interface DeliveryTaskRepository {
    // Select operations
    List<DeliveryTaskDTO> selectAllDeliveryTasks();

    List<DeliveryTaskDTO> selectAllDeliveryTasksByCourierId(String id);

    Optional<DeliveryTaskDTO> selectDeliveryTaskByOrderId(String id);

    Optional<DeliveryTaskDTO> selectDeliveryTaskById(String id);

    // Lock operation
    Optional<DeliveryTaskDTO> lockDeliveryTaskById(String id);

    // Insert operation
    boolean addDeliveryTask(DeliveryTaskDTO deliveryTaskDTO);

    // Update operation
    boolean updateDeliveryTask(DeliveryTaskDTO deliveryTaskDTO);

    // Delete operation
    boolean deleteDeliveryTaskById(String id);
}
