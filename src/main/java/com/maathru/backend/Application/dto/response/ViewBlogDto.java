package com.maathru.backend.Application.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewBlogDto {
    private String title;
    private String content;
    private String author;
    private String image;
    private String location;
    private String approvalStatus;
    private String statusReason;

}
