package com.tracktive.userservice.util;

import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.model.entity.Courier;
import java.util.Objects;

/**
* Description: Util to convert Courier Model
* @author William Theo
* @date 4/3/2025
*/
public class CourierConverter {
    // Private Constructor to prevent instantiation
    private CourierConverter() {
    }

    public static CourierDTO toDTO(Courier courier){
        if (Objects.isNull(courier)){
            return null;
        }
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setCourierId(courier.getCourierId());
        courierDTO.setDrivingLicenseNumber(courier.getDrivingLicenseNumber());
        courierDTO.setPlateNumber(courier.getPlateNumber());
        courierDTO.setPreferredDeliveryZone(courier.getPreferredDeliveryZone());
        courierDTO.setBankAccount(courier.getBankAccount());
        courierDTO.setBankName(courier.getBankName());
        return courierDTO;
    }

    public static Courier toEntity(CourierDTO courierDTO){
        if (Objects.isNull(courierDTO)){
            return null;
        }
        Courier courier = new Courier();
        courier.setCourierId(courierDTO.getCourierId());
        courier.setDrivingLicenseNumber(courierDTO.getDrivingLicenseNumber());
        courier.setPlateNumber(courierDTO.getPlateNumber());
        courier.setPreferredDeliveryZone(courierDTO.getPreferredDeliveryZone());
        courier.setBankAccount(courierDTO.getBankAccount());
        courier.setBankName(courierDTO.getBankName());
        return courier;
    }
}
