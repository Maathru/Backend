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
    @Column(name = "time_stamp")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;

    @Column(name = "content")
    private String content;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;


}