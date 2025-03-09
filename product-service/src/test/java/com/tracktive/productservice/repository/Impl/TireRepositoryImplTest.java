package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;
import com.tracktive.productservice.model.entity.Tire;
import com.tracktive.productservice.repository.TireRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TireRepositoryImplTest {

    @Autowired
    private TireRepository tireRepository;
    private static final Logger logger = LoggerFactory.getLogger(TireRepositoryImplTest.class);

    @Test
    void selectAllTire() {
        List<Tire> tires = tireRepository.selectAllTire();
        logger.info("Tires:{}", tires);
        assertEquals(tires.isEmpty(),false);
    }

    @Test
    void selectTireByParams() {
        Tire tire = new Tire();
        tire.setId("");
        tire.setTireSku("BRD-205-55R16-91V");
        List<Tire> tires = tireRepository.selectTireByParams(tire);
        logger.info("Result:{}",tires.getFirst().getTireSku());
        assertEquals(tires.isEmpty(),false);
    }

    @Test
    void selectTireById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Tire> tire = tireRepository.selectTireById(id);
        Tire tireResult = tire.get();
        logger.info("Result:{}",tireResult.getRunFlat());
        assertEquals(id, tireResult.getId());
    }

    @Test
    void lockTireById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Tire> tire = tireRepository.lockTireById(id);
        Tire tireResult = tire.get();
        logger.info("Result:{}",tireResult.getRunFlat());
        assertEquals(id, tireResult.getId());
    }

    @Test
    void selectTireBySKU() {
        String sku = "BRD-205-55R16-91V";
        Optional<Tire> tire = tireRepository.selectTireBySKU(sku);
        Tire tireResult = tire.get();
        assertEquals(sku, tireResult.getTireSku());
    }

    @Test
    void addTire() {
        Tire tire = new Tire();
        tire.setId("01edbd0f-863f-4e65-b81d-025cdd430aa3");
        tire.setTireSku("BRD-205-55R16-91V");
        tire.setWidth(205);
        tire.setAspectRatio(55);
        tire.setRimDiameter(16);
        tire.setConstructionType("R");
        tire.setLoadIndex("91");
        tire.setSpeedRating("V");
        tire.setTireSeason(TireSeason.SUMMER);
        tire.setTreadPattern(TreadPattern.SYMMETRICAL);
        tire.setTireType(TireType.TOURING);
        tire.setRunFlat(true);

        boolean isAdded = tireRepository.addTire(tire);
        assertTrue(isAdded);
    }

    @Test
    void updateTire() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Tire> tire = tireRepository.selectTireById(id);
        Tire tireResult = tire.get();
        tireResult.setWidth(215);
        boolean updateResult = tireRepository.updateTire(tireResult);
        assertTrue(updateResult);
    }

    @Test
    void deleteById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        boolean delResult = tireRepository.deleteById(id);
        assertTrue(delResult);
    }
}