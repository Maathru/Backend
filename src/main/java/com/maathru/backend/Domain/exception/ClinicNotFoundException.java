package com.maathru.backend.Domain.exception;

public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
