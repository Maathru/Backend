package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "clinical_conservations")
public class ClinicalConservation extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private LocalDate clinicDate;
    private int weeksIntoPregnancy;
    private float weight;
    private float height;
    private float bmi;
    private float glucoseLevel;
    private float albuminLevel;
    private boolean swelling;
    private int bloodPressure;
    private float fetalHeight;
    private String fetalLocation;
    private String fetalMovements;
    private String heartSound;
    private String ironFolate;
    private String vitaminC;
    private String calciumMalaria;
    private String thriposha;
    private String bloodSample;
    private String bloodSugarLevel;
    private String hemoglobinLevel;
    private String malaria;
    private String vdrlResult;
    private String lungs;
    private String dentalTests;
    private String dentalDrying;
    private String galagandaya;
    private String checkedBy;
    private String referral;

    @ManyToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false)
    private User user;
}
