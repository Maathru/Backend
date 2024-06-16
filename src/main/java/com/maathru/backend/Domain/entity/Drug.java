package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id", nullable = false)
    private Long drugId;

    @Column(name = "composition")
    private String composition;

    @Column(name = "strength")
    private String strength;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "batch_number", nullable = false)
    private String batchNumber;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "manufactured_date")
    private LocalDate manufacturedDate;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "recommended_dose")
    private String recommendedDose;

}

