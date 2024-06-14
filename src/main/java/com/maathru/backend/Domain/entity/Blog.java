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
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id", nullable = false)
    private Long blogId;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "location")
    private String location;

    @Column(name = "status_reason")
    private String statusReason;

    @Lob
    @Column(name = "content")
    private String content;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "time_stamp")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;

}