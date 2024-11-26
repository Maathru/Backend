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
    private boolean fetalLocation;
    private boolean fetalMovements;
    private boolean heartSound;
    private boolean ironFolate;
    private boolean vitaminC;
    private boolean calciumMalaria;
    private boolean thriposha;
    private boolean bloodSample;
    private boolean bloodSugarLevel;
    private boolean hemoglobinLevel;
    private boolean malaria;
    private boolean vdrlResult;
    private boolean lungs;
    private boolean dentalTests;
    private boolean dentalDrying;
    private boolean galagandaya;
    private boolean checkedBy;
    private boolean referral;

    @ManyToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false)
    private User user;
}
