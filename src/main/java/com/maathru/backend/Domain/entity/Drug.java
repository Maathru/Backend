package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "drug")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugId;
    private String composition;
    private String strength;
    private String brandName;
    private Integer quantity;
    private String batchNumber;
    private String recommendedDose;
    private LocalDate expiryDate;
    private LocalDate manufacturedDate;
    private LocalDate receivedDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime registeredTime;
}