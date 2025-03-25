package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DTO.CourierDTO;
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
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setCourierId("99998c87-1674-4448-89e9-1fe00286ab4d");
        courierDTO.setDrivingLicenseNumber("2342342342");
        courierDTO.setPlateNumber("SWG6868");
        courierDTO.setPreferredDeliveryZone("MELAKA");
        courierDTO.setBankAccount("93945789345");
        courierDTO.setBankName("CIMB");

        boolean result = courierRepository.addCourier(courierDTO);
        assertTrue(result);
    }


    @Test
    void selectAllCouriers() {
        List<CourierDTO> courierDTOS = courierRepository.selectAllCouriers();
        logger.info("Couriers:{}", courierDTOS);
        assertEquals(courierDTOS.isEmpty(),false);
    }

    @Test
    void selectCourierById() {
        String id = "99998c87-1674-4448-89e9-1fe00286ab4d";
        Optional<CourierDTO> courierDTO = courierRepository.selectCourierById(id);
        CourierDTO result = courierDTO.get();
        assertEquals(id, result.getCourierId());
    }

    @Test
    void lockCourierById() {
        String id = "99998c87-1674-4448-89e9-1fe00286ab4d";
        Optional<CourierDTO> courierDTO = courierRepository.selectCourierById(id);
        logger.info("Acquired lock for user: " + courierDTO.get().getCourierId());
    }

    @Test
    void updateCourier() {
        Optional<CourierDTO> courierDTO = courierRepository.selectCourierById("99998c87-1674-4448-89e9-1fe00286ab4d");
        CourierDTO updateCourierDTO = courierDTO.get();
        logger.info("User Name:{}", updateCourierDTO.getBankName());
        updateCourierDTO.setBankName("PUBLIC BANK");
        assertTrue(courierRepository.updateCourier(updateCourierDTO));
    }

    @Test
    void deleteById() {
        String id = "99998c87-1674-4448-89e9-1fe00286ab4d";
        boolean deleteResult = courierRepository.deleteById(id);
        Optional<CourierDTO> courierDTO = courierRepository.selectCourierById(id);
        assertEquals(courierDTO, Optional.empty());
        assertTrue(deleteResult);
    }
}