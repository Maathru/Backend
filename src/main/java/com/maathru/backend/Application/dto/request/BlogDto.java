package com.maathru.backend.Application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;

@Getter
@Setter
public class BlogDto {

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Category cannot be empty")
    private String category;
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    private String additionalNotes;
    private List<String> keywords;
    private String image;
}