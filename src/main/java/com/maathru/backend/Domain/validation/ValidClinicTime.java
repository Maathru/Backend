package com.maathru.backend.Domain.validation;

import com.maathru.backend.Domain.validation.impl.ClinicTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClinicTimeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidClinicTime {
    String message() default "Invalid clinic times";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
