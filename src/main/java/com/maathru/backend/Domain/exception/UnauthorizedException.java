package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
        log.error(message);
    }
}
