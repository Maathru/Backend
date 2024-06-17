package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private String answer;
    private long question;
    private long author;
}
