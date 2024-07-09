package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private String accessToken;
    private String refreshToken;
    @Column(name = "is_logged_out")
    private boolean loggedOut;
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;
}
