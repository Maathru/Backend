package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

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

    @Column(length = 2048)
    @NotNull
    private String description;

    private String keywords;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answers;

    // Utility methods to convert between List<String> and comma-separated String
    public List<String> getKeywords() {
        return keywords == null ? Collections.emptyList() : Arrays.asList(keywords.split(","));
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = String.join(",", keywords);
    }
}
