package com.tracktive.productservice.util;

import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.entity.Tire;
import java.util.Objects;

/**
* Description: Util for convert Tire Model
* @author William Theo
* @date 9/3/2025
*/
public class TireConverter {

    // Private constructor to prevent instantiation
    private TireConverter() {}

    public static TireDTO toDTO(Tire tire) {
        if (Objects.isNull(tire)) {
            return null;
        }
        TireDTO tireDTO = new TireDTO();
        tireDTO.setId(tire.getId());
        tireDTO.setTireSku(tire.getTireSku());
        tireDTO.setWidth(tire.getWidth());
        tireDTO.setAspectRatio(tire.getAspectRatio());
        tireDTO.setRimDiameter(tire.getRimDiameter());
        tireDTO.setConstructionType(tire.getConstructionType());
        tireDTO.setLoadIndex(tire.getLoadIndex());
        tireDTO.setSpeedRating(tire.getSpeedRating());
        tireDTO.setTireSeason(tire.getTireSeason());
        tireDTO.setTreadPattern(tire.getTreadPattern());
        tireDTO.setTireType(tire.getTireType());
        tireDTO.setRunFlat(tire.getRunFlat());
        return tireDTO;
    }

    public static Tire toEntity(TireDTO tireDTO) {
        if (Objects.isNull(tireDTO)) {
            return null;
        }
        Tire tire = new Tire();
        tire.setId(tireDTO.getId());
        tire.setTireSku(tireDTO.getTireSku());
        tire.setWidth(tireDTO.getWidth());
        tire.setAspectRatio(tireDTO.getAspectRatio());
        tire.setRimDiameter(tireDTO.getRimDiameter());
        tire.setConstructionType(tireDTO.getConstructionType());
        tire.setLoadIndex(tireDTO.getLoadIndex());
        tire.setSpeedRating(tireDTO.getSpeedRating());
        tire.setTireSeason(tireDTO.getTireSeason());
        tire.setTreadPattern(tireDTO.getTreadPattern());
        tire.setTireType(tireDTO.getTireType());
        tire.setRunFlat(tireDTO.getRunFlat());
        return tire;
    }
}
