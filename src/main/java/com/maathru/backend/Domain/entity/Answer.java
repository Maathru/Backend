package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "question_question_id")
    private Question question;

    @Column(name = "content")
    private String content;

    @Column(name = "time_stamp")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime timeStamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;

}