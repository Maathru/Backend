package com.maathru.backend.Application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class QuestionDto {
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @Length(max = 2048, message = "Description must be less than 2048 characters")
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    private List<String> keywords;
}