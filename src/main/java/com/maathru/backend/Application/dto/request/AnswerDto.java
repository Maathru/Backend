package com.maathru.backend.Application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AnswerDto {
    @Length(max = 2048, message = "Answer must be less than 2048 characters")
    @NotEmpty(message = "Answer cannot be empty")
    private String answer;
    @NotNull(message = "Question id cannot be empty")
    private long question;
}
