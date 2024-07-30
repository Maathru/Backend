package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponse {
    private long id;
    private String answer;
    private String authorName;
    private long authorId;
    private String createdAt;
}
