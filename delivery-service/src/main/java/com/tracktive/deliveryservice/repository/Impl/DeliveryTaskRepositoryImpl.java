package com.tracktive.deliveryservice.repository.Impl;

import com.tracktive.deliveryservice.exception.DatabaseOperationException;
import com.tracktive.deliveryservice.exception.DeliveryTaskAlreadyExistException;
import com.tracktive.deliveryservice.model.DAO.DeliveryTaskDAO;
import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.entity.DeliveryTask;
import com.tracktive.deliveryservice.repository.DeliveryTaskRepository;
import com.tracktive.deliveryservice.util.DeliveryTaskConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Delivery Task Repository Implementation
* @author William Theo
* @date 21/3/2025
*/
@Repository
public class DeliveryTaskRepositoryImpl implements DeliveryTaskRepository {

    private final DeliveryTaskDAO deliveryTaskDAO;

    @Autowired
    public DeliveryTaskRepositoryImpl(DeliveryTaskDAO deliveryTaskDAO) {
        this.deliveryTaskDAO = deliveryTaskDAO;
    }

    @Override
    public List<DeliveryTaskDTO> selectAllDeliveryTasks() {
        return deliveryTaskDAO.selectAllDeliveryTasks()
                .stream()
                .map(DeliveryTaskConverter::toDTO)
                .toList();
    }

    @Override
    public List<DeliveryTaskDTO> selectAllDeliveryTasksByCourierId(String id) {
        return deliveryTaskDAO.selectAllDeliveryTasksByCourierId(id)
                .stream()
                .map(DeliveryTaskConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<DeliveryTaskDTO> selectDeliveryTaskByOrderId(String id) {
        return deliveryTaskDAO.selectDeliveryTaskByOrderId(id).map(DeliveryTaskConverter::toDTO);
    }

    @Override
    public Optional<DeliveryTaskDTO> selectDeliveryTaskById(String id) {
        return deliveryTaskDAO.selectDeliveryTaskById(id).map(DeliveryTaskConverter::toDTO);
    }

    @Override
    public Optional<DeliveryTaskDTO> lockDeliveryTaskById(String id) {
        return deliveryTaskDAO.lockDeliveryTaskById(id).map(DeliveryTaskConverter::toDTO);
    }

    @Override
    public boolean addDeliveryTask(DeliveryTaskDTO deliveryTaskDTO) {
        try {
            DeliveryTask deliveryTask = DeliveryTaskConverter.toEntity(deliveryTaskDTO);
            return deliveryTaskDAO.addDeliveryTask(deliveryTask) > 0;
        } catch (DuplicateKeyException e) {
            throw new DeliveryTaskAlreadyExistException("Delivery Task with id " + deliveryTaskDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add delivery task to the database", e);
        }
    }

    @Override
    public boolean updateDeliveryTask(DeliveryTaskDTO deliveryTaskDTO) {
        DeliveryTask deliveryTask = DeliveryTaskConverter.toEntity(deliveryTaskDTO);
        return deliveryTaskDAO.updateDeliveryTask(deliveryTask) > 0;
    }

    @Override
    public boolean deleteDeliveryTaskById(String id) {
        return deliveryTaskDAO.deleteDeliveryTaskById(id) > 0;
    }
}
