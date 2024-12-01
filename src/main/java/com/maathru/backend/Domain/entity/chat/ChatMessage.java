package com.maathru.backend.Domain.entity.chat;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cha_messages")
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;
    private String chatId;
    private long senderId;
    private long recipientId;
    private String content;
    private Date timestamp;
}
