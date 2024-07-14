package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "question_question_id")
    private Question question;
    @NotNull
    private String answer;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by_user_id")
    private User updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        setCreatedAt(now());
        setUpdatedAt(now());
    }

    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(now());
    }
}