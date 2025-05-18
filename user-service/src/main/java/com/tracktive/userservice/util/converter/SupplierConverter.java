package com.tracktive.userservice.util.converter;

import com.tracktive.userservice.model.DTO.*;
import com.tracktive.userservice.model.entity.Supplier;
import java.util.Objects;

/**
* Description: Util for convert Supplier model
* @author William Theo
* @date 4/3/2025
*/
public class SupplierConverter {

    // Private Constructor to prevent instantiation
    private SupplierConverter() {
    }

    public static SupplierDTO toDTO(Supplier supplier){
        if (Objects.isNull(supplier)){
            return null;
        }
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getSupplierId());
        supplierDTO.setSsmRegistrationNumber(supplier.getSsmRegistrationNumber());
        supplierDTO.setBusinessName(supplier.getBusinessName());
        supplierDTO.setBusinessAddress(supplier.getBusinessAddress());
        supplierDTO.setBankAccount(supplier.getBankAccount());
        supplierDTO.setBankName(supplier.getBankName());
        return supplierDTO;
    }

    public static Supplier toEntity(SupplierDTO supplierDTO){
        if (Objects.isNull(supplierDTO)){
            return null;
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDTO.getSupplierId());
        supplier.setSsmRegistrationNumber(supplierDTO.getSsmRegistrationNumber());
        supplier.setBusinessName(supplierDTO.getBusinessName());
        supplier.setBusinessAddress(supplierDTO.getBusinessAddress());
        supplier.setBankAccount(supplierDTO.getBankAccount());
        supplier.setBankName(supplierDTO.getBankName());
        return supplier;
    }

    public static SupplierDTO toDTO(UserDTO userDTO, SupplierDetailsDTO supplierDetailsDTO){
        if (Objects.isNull(userDTO) || Objects.isNull(supplierDetailsDTO)) {
            return null;
        }
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(userDTO.getId());
        supplierDTO.setSsmRegistrationNumber(supplierDetailsDTO.getSsmRegistrationNumber());
        supplierDTO.setBusinessName(supplierDetailsDTO.getBusinessName());
        supplierDTO.setBusinessAddress(supplierDetailsDTO.getBusinessAddress());
        supplierDTO.setBankAccount(supplierDetailsDTO.getBankAccount());
        supplierDTO.setBankName(supplierDetailsDTO.getBankName());
        return supplierDTO;
    }

}
