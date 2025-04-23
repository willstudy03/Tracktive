package com.tracktive.userservice.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserCreationValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserCreation {

    String message() default "Invalid user creation request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
