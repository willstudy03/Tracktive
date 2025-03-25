package com.tracktive.deliveryservice.repository.Impl;

import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.Enum.DeliveryStatus;
import com.tracktive.deliveryservice.model.Enum.DeliveryType;
import com.tracktive.deliveryservice.model.entity.DeliveryTask;
import com.tracktive.deliveryservice.repository.DeliveryTaskRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
* Description: Delivery Task Repository CRUD Test Case
* @author William Theo
* @date 21/3/2025
*/
@SpringBootTest
class DeliveryTaskRepositoryImplTest {

    @Autowired
    private DeliveryTaskRepository deliveryTaskRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeliveryTaskRepositoryImplTest.class);

    @Test
    void selectAllDeliveryTasks() {
        List<DeliveryTaskDTO> deliveryTaskDTOs = deliveryTaskRepository.selectAllDeliveryTasks();
        logger.info("Delivery Task:{}", deliveryTaskDTOs);
        assertEquals(deliveryTaskDTOs.isEmpty(),false);
    }

    @Test
    void selectAllDeliveryTasksByCourierId() {
        String id = "C12345";
        List<DeliveryTaskDTO> deliveryTaskDTOs = deliveryTaskRepository.selectAllDeliveryTasksByCourierId(id);
        logger.info("Delivery Task:{}", deliveryTaskDTOs);
        assertEquals(deliveryTaskDTOs.get(0).getCourierId(), id);
    }

    @Test
    void selectDeliveryTaskByOrderId() {
        String id = "ORD987654";
        Optional<DeliveryTaskDTO> deliveryTaskDTOs = deliveryTaskRepository.selectDeliveryTaskByOrderId(id);
        logger.info("Delivery Task:{}", deliveryTaskDTOs);
        assertEquals(deliveryTaskDTOs.get().getOrderId(), id);
    }

    @Test
    void selectDeliveryTaskById() {
        String id = "DT123456";
        Optional<DeliveryTaskDTO> deliveryTaskDTOs = deliveryTaskRepository.selectDeliveryTaskById(id);
        logger.info("Delivery Task:{}", deliveryTaskDTOs);
        assertEquals(deliveryTaskDTOs.get().getId(), id);
    }

    @Test
    void lockDeliveryTaskById() {
        String id = "DT123456";
        Optional<DeliveryTaskDTO> deliveryTaskDTOs = deliveryTaskRepository.lockDeliveryTaskById(id);
        logger.info("Delivery Task:{}", deliveryTaskDTOs);
        assertEquals(deliveryTaskDTOs.get().getId(), id);
    }

    @Test
    void addDeliveryTask() {
        DeliveryTaskDTO deliveryTask = new DeliveryTaskDTO();

        deliveryTask.setId("DT123456");
        deliveryTask.setOrderId("ORD987654");
        deliveryTask.setCourierId("C12345");
        deliveryTask.setRecipientId("R56789");
        deliveryTask.setDeliveryType(DeliveryType.COURIER_DELIVERY); // Example enum value
        deliveryTask.setPickUpAddress("123, Jalan Bukit Bintang, Kuala Lumpur, Malaysia");
        deliveryTask.setDestinationAddress("456, Jalan Ampang, Kuala Lumpur, Malaysia");
        deliveryTask.setCurrentLatitude(3.1390);
        deliveryTask.setCurrentLongitude(101.6869);
        deliveryTask.setStartedAt(LocalDateTime.now());
        deliveryTask.setCompletedAt(null); // Not completed yet
        deliveryTask.setDeliveryStatus(DeliveryStatus.IN_TRANSIT); // Example enum value

        boolean result = deliveryTaskRepository.addDeliveryTask(deliveryTask);
        assertTrue(result);
    }

    @Test
    void updateDeliveryTask() {
        Optional<DeliveryTaskDTO> deliveryTask = deliveryTaskRepository.selectDeliveryTaskById("DT123456");
        DeliveryTaskDTO updateDeliveryTask = deliveryTask.get();
        logger.info("Delivery Task Status:{}", updateDeliveryTask.getDeliveryStatus());
        updateDeliveryTask.setDeliveryStatus(DeliveryStatus.DELIVERED);
        assertTrue(deliveryTaskRepository.updateDeliveryTask(updateDeliveryTask));
    }

    @Test
    void deleteDeliveryTaskById() {
        String id = "DT123456";
        boolean delResult = deliveryTaskRepository.deleteDeliveryTaskById(id);
        assertTrue(delResult);
    }
}