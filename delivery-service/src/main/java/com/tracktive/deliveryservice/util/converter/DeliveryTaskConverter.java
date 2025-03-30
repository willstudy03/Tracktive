package com.tracktive.deliveryservice.util.converter;

import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.DTO.DeliveryTaskRequestDTO;
import com.tracktive.deliveryservice.model.entity.DeliveryTask;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Delivery Task Model
* @author William Theo
* @date 21/3/2025
*/
public class DeliveryTaskConverter {
    // Private constructor to prevent instantiation
    private DeliveryTaskConverter() {
    }

    public static DeliveryTaskDTO toDTO(DeliveryTask deliveryTask) {
        if (Objects.isNull(deliveryTask)) {
            return null;
        }
        DeliveryTaskDTO deliveryTaskDTO = new DeliveryTaskDTO();
        deliveryTaskDTO.setId(deliveryTask.getId());
        deliveryTaskDTO.setOrderId(deliveryTask.getOrderId());
        deliveryTaskDTO.setCourierId(deliveryTask.getCourierId());
        deliveryTaskDTO.setRecipientId(deliveryTask.getRecipientId());
        deliveryTaskDTO.setDeliveryType(deliveryTask.getDeliveryType());
        deliveryTaskDTO.setPickUpAddress(deliveryTask.getPickUpAddress());
        deliveryTaskDTO.setDestinationAddress(deliveryTask.getDestinationAddress());
        deliveryTaskDTO.setCurrentLatitude(deliveryTask.getCurrentLatitude());
        deliveryTaskDTO.setCurrentLongitude(deliveryTask.getCurrentLongitude());
        deliveryTaskDTO.setStartedAt(deliveryTask.getStartedAt());
        deliveryTaskDTO.setCompletedAt(deliveryTask.getCompletedAt());
        deliveryTaskDTO.setDeliveryStatus(deliveryTask.getDeliveryStatus());
        deliveryTaskDTO.setUpdatedAt(deliveryTask.getUpdatedAt());
        deliveryTaskDTO.setCreatedAt(deliveryTask.getCreatedAt());
        return deliveryTaskDTO;
    }

    public static DeliveryTaskDTO toDTO(DeliveryTaskRequestDTO deliveryTaskRequestDTO) {
        if (Objects.isNull(deliveryTaskRequestDTO)) {
            return null;
        }
        DeliveryTaskDTO deliveryTaskDTO = new DeliveryTaskDTO();
        deliveryTaskDTO.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(deliveryTaskRequestDTO, deliveryTaskDTO);
        return deliveryTaskDTO;
    }

    public static DeliveryTask toEntity(DeliveryTaskDTO deliveryTaskDTO) {
        if (Objects.isNull(deliveryTaskDTO)) {
            return null;
        }
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setId(deliveryTaskDTO.getId());
        deliveryTask.setOrderId(deliveryTaskDTO.getOrderId());
        deliveryTask.setCourierId(deliveryTaskDTO.getCourierId());
        deliveryTask.setRecipientId(deliveryTaskDTO.getRecipientId());
        deliveryTask.setDeliveryType(deliveryTaskDTO.getDeliveryType());
        deliveryTask.setPickUpAddress(deliveryTaskDTO.getPickUpAddress());
        deliveryTask.setDestinationAddress(deliveryTaskDTO.getDestinationAddress());
        deliveryTask.setCurrentLatitude(deliveryTaskDTO.getCurrentLatitude());
        deliveryTask.setCurrentLongitude(deliveryTaskDTO.getCurrentLongitude());
        deliveryTask.setStartedAt(deliveryTaskDTO.getStartedAt());
        deliveryTask.setCompletedAt(deliveryTaskDTO.getCompletedAt());
        deliveryTask.setDeliveryStatus(deliveryTaskDTO.getDeliveryStatus());
        deliveryTask.setUpdatedAt(deliveryTaskDTO.getUpdatedAt());
        deliveryTask.setCreatedAt(deliveryTaskDTO.getCreatedAt());
        return deliveryTask;
    }
}
