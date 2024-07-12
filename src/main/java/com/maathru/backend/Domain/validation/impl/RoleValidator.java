package com.maathru.backend.Domain.validation.impl;

import com.maathru.backend.Domain.validation.ValidRole;
import com.maathru.backend.enumeration.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        if (role == null || role.isEmpty()) {
            return true; // Allow null or empty values
        }
        return Arrays.stream(Role.values())
                .anyMatch(enumRole -> enumRole.name().equals(role));
    }
}