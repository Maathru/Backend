package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
    private String question;
    private long author;
}