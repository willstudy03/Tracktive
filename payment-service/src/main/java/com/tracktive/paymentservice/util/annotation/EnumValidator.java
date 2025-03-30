package com.tracktive.paymentservice.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, Object> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values, handled by @NotNull
        }

        if (value instanceof String stringValue) {
            return Arrays.stream(enumClass.getEnumConstants())
                    .map(Enum::name)
                    .anyMatch(name -> name.equals(stringValue));
        } else if (value instanceof Enum<?>) {
            return Arrays.asList(enumClass.getEnumConstants()).contains(value);
        }

        return false;
    }
}
