package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentNotFoundException extends RuntimeException {
    public ParentNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
