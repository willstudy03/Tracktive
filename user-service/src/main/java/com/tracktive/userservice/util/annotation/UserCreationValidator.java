package com.tracktive.userservice.util.annotation;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.Enum.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserCreationValidator implements ConstraintValidator<ValidUserCreation, UserCreationRequestDTO> {

    @Override
    public boolean isValid(UserCreationRequestDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true; // Let @NotNull handle null DTOs

        boolean isValid = true;
        context.disableDefaultConstraintViolation();

        if (dto.getUserRole() == UserRole.RETAILER && dto.getRetailerDetailsDTO() == null) {
            context.buildConstraintViolationWithTemplate("Retailer details are required for RETAILER role")
                    .addPropertyNode("retailerDetailsDTO")
                    .addConstraintViolation();
            isValid = false;
        }

        if (dto.getUserRole() == UserRole.SUPPLIER && dto.getSupplierDetailsDTO() == null) {
            context.buildConstraintViolationWithTemplate("Supplier details are required for SUPPLIER role")
                    .addPropertyNode("supplierDetailsDTO")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}