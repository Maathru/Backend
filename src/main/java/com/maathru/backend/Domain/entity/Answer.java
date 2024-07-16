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
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "question_question_id")
    private Question question;
    @NotNull
    private String answer;
}