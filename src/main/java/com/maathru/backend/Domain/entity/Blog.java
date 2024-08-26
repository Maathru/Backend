package com.maathru.backend.Domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Blog extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    @NotNull
    private String title;
    @Column(length = 10000)
    @NotNull
    private String content;
    @NotNull
    private String category;
    private String additionalNotes;
    private String image;
    private String location;
    private String approvalStatus;
    private String statusReason;

    private String keywords;

    // Utility methods to convert between List<String> and comma-separated String
    public List<String> getKeywords() {
        return keywords == null ? Collections.emptyList() : Arrays.asList(keywords.split(","));
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = String.join(",", keywords);
    }

}