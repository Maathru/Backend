package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MOHNotFoundException extends RuntimeException {
    public MOHNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
