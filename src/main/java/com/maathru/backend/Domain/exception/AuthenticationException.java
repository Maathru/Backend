package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
        log.error(message);
    }
}