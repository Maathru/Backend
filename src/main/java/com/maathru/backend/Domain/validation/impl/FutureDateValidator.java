package com.maathru.backend.Domain.validation.impl;

import com.maathru.backend.Domain.validation.FutureDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    @Override
    public void initialize(FutureDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true; // NotEmpty will handle the null case
        }
        date = date.plusDays(1);
        return date.isAfter(LocalDate.now());
    }
}
