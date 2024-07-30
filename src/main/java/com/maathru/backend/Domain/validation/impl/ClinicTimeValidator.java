package com.maathru.backend.Domain.validation.impl;


import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.validation.ValidClinicTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class ClinicTimeValidator implements ConstraintValidator<ValidClinicTime, ClinicDto> {

    private final LocalTime officeStartTime = LocalTime.of(3, 30);
    private final LocalTime officeEndTime = LocalTime.of(11, 30);

    @Override
    public void initialize(ValidClinicTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClinicDto clinicDto, ConstraintValidatorContext context) {
        if (clinicDto == null) {
            return true; // Skip validation for null object
        }

        LocalTime startTime = LocalTime.from(clinicDto.getStartTime());
        LocalTime endTime = LocalTime.from(clinicDto.getEndTime());

        if (startTime == null || endTime == null) {
            return false; // Validation fails if either time is null
        }

        if (startTime.isBefore(officeStartTime) || startTime.isAfter(officeEndTime)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Start time must be within office hours (9:00 AM to 5:00 PM)")
                    .addPropertyNode("startTime")
                    .addConstraintViolation();
            return false;
        }

        if (endTime.isBefore(officeStartTime) || endTime.isAfter(officeEndTime)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End time must be within office hours (9:00 AM to 5:00 PM)")
                    .addPropertyNode("endTime")
                    .addConstraintViolation();
            return false;
        }

        if (!endTime.isAfter(startTime)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End time must be later than start time")
                    .addPropertyNode("endTime")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}