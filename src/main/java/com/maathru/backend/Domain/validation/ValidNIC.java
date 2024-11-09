package com.maathru.backend.Domain.validation;

import com.maathru.backend.Domain.validation.impl.NICValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NICValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNIC {
    String message() default "Invalid NIC";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
