package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
    private String question;
    private Long authorUserId;
}
