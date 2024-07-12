package com.maathru.backend.Domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
