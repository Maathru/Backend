package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@Getter
@Setter
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long questionId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private String keywords;

    // Utility methods to convert between List<String> and comma-separated String
    public List<String> getKeywords() {
        return keywords == null ? Collections.emptyList() : Arrays.asList(keywords.split(","));
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = String.join(",", keywords);
    }
}