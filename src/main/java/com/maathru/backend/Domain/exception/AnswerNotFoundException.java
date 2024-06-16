package com.maathru.backend.Domain.exception;

public class AnswerNotFoundException extends RuntimeException{
    public AnswerNotFoundException(String message){
        super(message);
    }
}
