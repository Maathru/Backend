package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "midwife_assessment")
public class MidwifeAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long midwifeAssessmentId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    // Women's details
    private double womanWeight; // in kg
    private double womanHeight; // in meters
    private double womanBMI; // Body mass index (BMI) kg/m^2
    private String womanBloodType;
    private double womanHemoglobinLevel;

    // Men's details
    private double manWeight; // in kg
    private double manHeight; // in meters
    private double manBMI; // Body mass index (BMI) kg/m^2
    private String manBloodType;
    private double manHemoglobinLevel;

    private String specialCases;

    @ElementCollection
    @CollectionTable(name = "counseling_dates", joinColumns = @JoinColumn(name = "midwife_assessment_id"))
    @Column(name = "counseling_date")
    private List<java.time.LocalDate> counselingDates;
}
