package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
