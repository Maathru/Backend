package com.maathru.backend.Domain.entity.chat;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_users")
@Getter
@Setter
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;
    @OneToOne
    private User user;
    private Status status;
    private LocalDateTime lastSeen;
}
