package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;
import com.tracktive.productservice.repository.TireRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TireRepositoryImplTest {

    @Autowired
    private TireRepository tireRepository;
    private static final Logger logger = LoggerFactory.getLogger(TireRepositoryImplTest.class);

    @Test
    void selectAllTire() {
        List<TireDTO> tireDTOS = tireRepository.selectAllTire();
        logger.info("Tires:{}", tireDTOS);
        assertEquals(tireDTOS.isEmpty(),false);
    }

    @Test
    void selectTireByParams() {
        TireDTO tireDTO = new TireDTO();
        tireDTO.setId("");
        tireDTO.setTireSku("abc");
        List<TireDTO> tireDTOS = tireRepository.selectTireByParams(tireDTO);
        assertEquals(tireDTOS.isEmpty(),true);
    }

    @Test
    void selectTireById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<TireDTO> tireDTO = tireRepository.selectTireById(id);
        TireDTO tireResult = tireDTO.get();
        logger.info("Result:{}",tireResult.getRunFlat());
        assertEquals(id, tireResult.getId());
    }

    @Test
    void lockTireById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<TireDTO> tireDTO = tireRepository.lockTireById(id);
        TireDTO tireResult = tireDTO.get();
        logger.info("Result:{}",tireResult.getRunFlat());
        assertEquals(id, tireResult.getId());
    }

    @Test
    void selectTireBySKU() {
        String sku = "BRD-205-55R16-91V";
        Optional<TireDTO> tireDTO = tireRepository.selectTireBySKU(sku);
        TireDTO tireResult = tireDTO.get();
        assertEquals(sku, tireResult.getTireSku());
    }

    @Test
    void addTire() {
        TireDTO tireDTO = new TireDTO();
        tireDTO.setId("01edbd0f-863f-4e65-b81d-025cdd430aa3");
        tireDTO.setTireSku("BRD-205-55R16-91V");
        tireDTO.setWidth(205);
        tireDTO.setAspectRatio(55);
        tireDTO.setRimDiameter(16);
        tireDTO.setConstructionType("R");
        tireDTO.setLoadIndex("91");
        tireDTO.setSpeedRating("V");
        tireDTO.setTireSeason(TireSeason.SUMMER);
        tireDTO.setTreadPattern(TreadPattern.SYMMETRICAL);
        tireDTO.setTireType(TireType.TOURING);
        tireDTO.setRunFlat(true);

        boolean isAdded = tireRepository.addTire(tireDTO);
        assertTrue(isAdded);
    }

    @Test
    void updateTire() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<TireDTO> tireDTO = tireRepository.selectTireById(id);
        TireDTO tireResult = tireDTO.get();
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