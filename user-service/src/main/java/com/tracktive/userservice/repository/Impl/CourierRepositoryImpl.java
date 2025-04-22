package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.exception.DatabaseOperationException;
import com.tracktive.userservice.exception.ForeignKeyConstraintException;
import com.tracktive.userservice.exception.UserAlreadyExistsException;
import com.tracktive.userservice.model.DAO.CourierDAO;
import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.model.entity.Courier;
import com.tracktive.userservice.repository.CourierRepository;
import com.tracktive.userservice.util.converter.CourierConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Description: Courier Repository Implementation
* @author William Theo
* @date 2/3/2025
*/
@Repository
public class CourierRepositoryImpl implements CourierRepository {

    private final CourierDAO courierDAO;

    @Autowired
    public CourierRepositoryImpl(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }

    @Override
    public List<CourierDTO> selectAllCouriers() {
        return Optional.ofNullable(courierDAO.selectAllCouriers())
                .orElse(Collections.emptyList())
                .stream()
                .map(CourierConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourierDTO> selectCourierById(String id) {
        return courierDAO.selectCourierById(id).map(CourierConverter::toDTO);
    }

    @Override
    public Optional<CourierDTO> lockCourierById(String id) {
        return courierDAO.lockCourierById(id).map(CourierConverter::toDTO);
    }

    @Override
    public boolean addCourier(CourierDTO courierDTO) {
        try {
            Courier courier = CourierConverter.toEntity(courierDTO);
            return courierDAO.addCourier(courier) > 0;
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("Courier with id " + courierDTO.getCourierId() + " already exists", e);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause(); // Get the root cause
            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintException("User with ID " + courierDTO.getCourierId() + " does not exist.", e);
            }
            throw new DatabaseOperationException("Database integrity constraint violation occurred.", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add courier to the database", e);
        }
    }

    @Override
    public boolean updateCourier(CourierDTO courierDTO) {
        Courier courier = CourierConverter.toEntity(courierDTO);
        return courierDAO.updateCourier(courier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return courierDAO.deleteCourierById(id) > 0;
    }

}
