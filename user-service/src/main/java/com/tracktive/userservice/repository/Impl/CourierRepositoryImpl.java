package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.CourierDAO;
import com.tracktive.userservice.model.entity.Courier;
import com.tracktive.userservice.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public List<Courier> selectAllCouriers() {
        return Optional.ofNullable(courierDAO.selectAllCouriers()).orElse(Collections.emptyList());
    }

    @Override
    public Optional<Courier> selectCourierById(String id) {
        return courierDAO.selectCourierById(id);
    }

    @Override
    public Optional<Courier> lockCourierById(String id) {
        return courierDAO.lockCourierById(id);
    }

    @Override
    public boolean addCourier(Courier courier) {
        return courierDAO.addCourier(courier) > 0;
    }

    @Override
    public boolean updateCourier(Courier courier) {
        return courierDAO.updateCourier(courier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return courierDAO.deleteCourierById(id) > 0;
    }
}
