package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.CourierDAO;
import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.model.entity.Courier;
import com.tracktive.userservice.repository.CourierRepository;
import com.tracktive.userservice.util.CourierConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        validateId(id);
        return courierDAO.selectCourierById(id).map(CourierConverter::toDTO);
    }

    @Override
    public Optional<CourierDTO> lockCourierById(String id) {
        validateId(id);
        return courierDAO.lockCourierById(id).map(CourierConverter::toDTO);
    }

    @Override
    public boolean addCourier(CourierDTO courierDTO) {
        validateCourierDTO(courierDTO);
        Courier courier = CourierConverter.toEntity(courierDTO);
        return courierDAO.addCourier(courier) > 0;
    }

    @Override
    public boolean updateCourier(CourierDTO courierDTO) {
        validateCourierDTO(courierDTO);
        Courier courier = CourierConverter.toEntity(courierDTO);
        return courierDAO.updateCourier(courier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return courierDAO.deleteCourierById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateCourierDTO(CourierDTO courierDTO) {
        if (Objects.isNull(courierDTO)) {
            throw new IllegalArgumentException("CourierDTO cannot be null");
        }
    }
}
