package com.maathru.backend.Domain.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
