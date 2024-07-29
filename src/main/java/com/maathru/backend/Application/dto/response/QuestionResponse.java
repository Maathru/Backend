package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
    private Long id;
    private String title;
    private String description;
    private List<String> keywords;
    private String authorName;
    private Long authorId;
    private String createdAt;
}
