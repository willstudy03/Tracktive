package com.tracktive.deliveryservice.model.DAO;

import com.tracktive.deliveryservice.model.entity.DeliveryTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Delivery Task DAO for mybatis mapping
* @author William Theo
* @date 21/3/2025
*/
@Mapper
public interface DeliveryTaskDAO {
    // Select operations
    List<DeliveryTask> selectAllDeliveryTasks();

    List<DeliveryTask> selectAllDeliveryTasksByCourierId(String id);

    Optional<DeliveryTask> selectDeliveryTaskByOrderId(String id);

    Optional<DeliveryTask> selectDeliveryTaskById(String id);

    // Lock operation
    Optional<DeliveryTask> lockDeliveryTaskById(String id);

    // Insert operation
    int addDeliveryTask(DeliveryTask deliveryTask);

    // Update operation
    int updateDeliveryTask(DeliveryTask deliveryTask);

    // Delete operation
    int deleteDeliveryTaskById(String id);
}
