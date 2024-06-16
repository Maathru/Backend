package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogDto {
    private String title;
    private String content;
    private User author;
    private String category;
    private String image;
    private String location;
    private String approvalStatus;
    private String statusReason;

}
