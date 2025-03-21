package com.tracktive.deliveryservice.repository;

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
    List<DeliveryTask> selectAllDeliveryTasks();

    List<DeliveryTask> selectAllDeliveryTasksByCourierId(String id);

    Optional<DeliveryTask> selectDeliveryTaskByOrderId(String id);

    Optional<DeliveryTask> selectDeliveryTaskById(String id);

    // Lock operation
    Optional<DeliveryTask> lockDeliveryTaskById(String id);

    // Insert operation
    boolean addDeliveryTask(DeliveryTask deliveryTask);

    // Update operation
    boolean updateDeliveryTask(DeliveryTask deliveryTask);

    // Delete operation
    boolean deleteDeliveryTaskById(String id);
}
