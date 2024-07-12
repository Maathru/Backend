package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
