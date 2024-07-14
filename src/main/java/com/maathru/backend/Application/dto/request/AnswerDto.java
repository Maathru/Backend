package com.maathru.backend.Application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    @NotEmpty(message = "Answer cannot be empty")
    private String answer;
    @NotNull(message = "Question id cannot be empty")
    private long question;
}
