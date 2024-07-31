package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class ViewBlogDto {
    private String title;
    private String content;
    private String author;
    private String category;
    private String image;
    private String location;
    private String approvalStatus;
    private String statusReason;
    private List<String> keywords;

    public ViewBlogDto(String title, String content, String author, String category, String image, String location, String approvalStatus, String statusReason, List<String> keywords) {
        this.category = category;
        this.author = author;
        this.content = content;
        this.title = title;
        this.image = image;
        this.location = location;
        this.approvalStatus = approvalStatus;
        this.statusReason = statusReason;
        this.keywords = keywords;
    }
}
