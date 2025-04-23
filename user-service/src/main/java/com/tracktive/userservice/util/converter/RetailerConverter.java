package com.tracktive.userservice.util.converter;

import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.DTO.RetailerDetailsDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.entity.Retailer;
import java.util.Objects;

/**
* Description: Util to convert Retailer model
* @author William Theo
* @date 4/3/2025
*/
public class RetailerConverter {

    // Private Constructor to prevent instantiation
    private RetailerConverter() {
    }

    public static RetailerDTO toDTO(Retailer retailer){
        if (Objects.isNull(retailer)){
            return null;
        }
        RetailerDTO retailerDTO = new RetailerDTO();
        retailerDTO.setRetailerId(retailer.getRetailerId());
        retailerDTO.setSsmRegistrationNumber(retailer.getSsmRegistrationNumber());
        retailerDTO.setBusinessName(retailer.getBusinessName());
        retailerDTO.setBusinessAddress(retailer.getBusinessAddress());
        retailerDTO.setBankAccount(retailer.getBankAccount());
        retailerDTO.setBankName(retailer.getBankName());
        retailerDTO.setPayByTermCredit(retailer.getPayByTermCredit());
        return retailerDTO;
    }

    public static Retailer toEntity(RetailerDTO retailerDTO){
        if (Objects.isNull(retailerDTO)){
            return null;
        }
        Retailer retailer = new Retailer();
        retailer.setRetailerId(retailerDTO.getRetailerId());
        retailer.setSsmRegistrationNumber(retailerDTO.getSsmRegistrationNumber());
        retailer.setBusinessName(retailerDTO.getBusinessName());
        retailer.setBusinessAddress(retailerDTO.getBusinessAddress());
        retailer.setBankAccount(retailerDTO.getBankAccount());
        retailer.setBankName(retailerDTO.getBankName());
        retailer.setPayByTermCredit(retailerDTO.getPayByTermCredit());
        return retailer;
    }

    public static RetailerDTO toDTO(UserDTO userDTO, RetailerDetailsDTO retailerDetailsDTO){
        if (Objects.isNull(userDTO) || Objects.isNull(retailerDetailsDTO)) {
            return null;
        }
        RetailerDTO retailerDTO = new RetailerDTO();
        retailerDTO.setRetailerId(userDTO.getId());

        // Set retailer-specific fields from retailerDetailsDTO
        retailerDTO.setSsmRegistrationNumber(retailerDetailsDTO.getSsmRegistrationNumber());
        retailerDTO.setBusinessName(retailerDetailsDTO.getBusinessName());
        retailerDTO.setBusinessAddress(retailerDetailsDTO.getBusinessAddress());
        retailerDTO.setBankAccount(retailerDetailsDTO.getBankAccount());
        retailerDTO.setBankName(retailerDetailsDTO.getBankName());
        retailerDTO.setPayByTermCredit(3);
        return retailerDTO;
    }
}
