package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private String answer;
    private long question;
    private long author;
}
