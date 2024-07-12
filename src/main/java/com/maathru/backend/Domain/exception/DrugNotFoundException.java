package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
