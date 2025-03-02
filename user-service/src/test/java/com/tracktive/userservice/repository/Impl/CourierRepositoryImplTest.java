package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.entity.Courier;
import com.tracktive.userservice.model.entity.User;
import com.tracktive.userservice.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourierRepositoryImplTest {

    @Autowired
    private CourierRepository courierRepository;
    private static final Logger logger = LoggerFactory.getLogger(CourierRepositoryImplTest.class);

    @Test
    void addCourier() {
        Courier courier = new Courier();
        courier.setCourierId("33130e6b-2296-4deb-82d5-ab04a4fbf30e");
        courier.setDrivingLicenseNumber("2342342342");
        courier.setPlateNumber("SWG6868");
        courier.setPreferredDeliveryZone("MELAKA");
        courier.setBankAccount("93945789345");
        courier.setBankName("CIMB");

        boolean result = courierRepository.addCourier(courier);
        assertTrue(result);
    }


    @Test
    void selectAllCouriers() {
        List<Courier> couriers = courierRepository.selectAllCouriers();
        logger.info("Couriers:{}", couriers);
        assertEquals(couriers.isEmpty(),false);
    }

    @Test
    void selectCourierById() {
        String id = "33130e6b-2296-4deb-82d5-ab04a4fbf30e";
        Optional<Courier> courier = courierRepository.selectCourierById(id);
        Courier result = courier.get();
        assertEquals(id, result.getCourierId());
    }

    @Test
    void lockCourierById() {
        String id = "33130e6b-2296-4deb-82d5-ab04a4fbf30e";
        Optional<Courier> courier = courierRepository.selectCourierById(id);
        logger.info("Acquired lock for user: " + courier.get().getCourierId());
    }

    @Test
    void updateCourier() {
        Optional<Courier> courier = courierRepository.selectCourierById("33130e6b-2296-4deb-82d5-ab04a4fbf30e");
        Courier updateCourier = courier.get();
        logger.info("User Name:{}", updateCourier.getBankName());
        updateCourier.setBankName("PUBLIC BANK");
        assertTrue(courierRepository.updateCourier(updateCourier));
    }

    @Test
    void deleteById() {
        String id = "33130e6b-2296-4deb-82d5-ab04a4fbf30e";
        boolean deleteResult = courierRepository.deleteById(id);
        Optional<Courier> courier = courierRepository.selectCourierById(id);
        assertEquals(courier, Optional.empty());
        assertTrue(deleteResult);
    }
}