package com.maathru.backend.Domain.validation.impl;

import com.maathru.backend.Domain.validation.PastDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    @Override
    public void initialize(PastDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true; // NotEmpty will handle the null case
        }
        return date.isBefore(LocalDate.now());
    }
}
