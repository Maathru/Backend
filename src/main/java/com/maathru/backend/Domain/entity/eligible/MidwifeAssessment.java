package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "midwife_assessment")
@Getter
@Setter
public class MidwifeAssessment extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    // Woman's details
    private double womanWeight; // in kg
    private double womanHeight; // in meters
    private double womanBmi; // Body mass index (BMI) kg/m^2
    private String womanBloodType;
    private double womanHemoglobin;

    // Men's details
    private double manWeight; // in kg
    private double manHeight; // in meters
    private double manBmi; // Body mass index (BMI) kg/m^2
    private String manBloodType;
    private double manHemoglobin;

    private String special;
    private String session;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
