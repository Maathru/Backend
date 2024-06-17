package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "moh")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MOH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mohId;

    @Column(unique = true)
    private String mohRegistrationNumber;
    private String province;
    private String district;
    private String division;
    private String addressLine1;
    private String street;
    private String city;

    @Column(unique = true)
    private String telephoneNumber;

    @Column(unique = true)
    private String faxNumber;
    private Long population;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = true)
    @JoinColumn(name = "current_head_id")
    private Employee currentHead;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime registeredTime;
}