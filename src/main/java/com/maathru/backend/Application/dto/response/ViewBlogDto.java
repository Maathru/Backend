package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ViewBlogDto {
    private String title;
    private String content;
    private long author;
    private String category;
    private String image;
    private String location;
    private String approvalStatus;
    private String statusReason;
    private List<String> keywords;

    public ViewBlogDto(String title, String content, long author, String category, String image, String location, String approvalStatus, String statusReason, String keywords) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.image = image;
        this.location = location;
        this.approvalStatus = approvalStatus;
        this.statusReason = statusReason;
        this.keywords = keywords == null ? Collections.emptyList() : Arrays.asList(keywords.split(","));
    }
}
