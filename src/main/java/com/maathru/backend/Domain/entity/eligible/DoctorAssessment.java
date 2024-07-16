package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctor_assessment")
public class DoctorAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long mohDoctorAssessmentId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    // Women's details
    private String womanGeneralExamination;
    private String womanBreastExamination;
    private String womanBP; // Blood Pressure
    private String womanCVC; // Cardiovascular System
    private String womanRS; // Respiratory System
    private String womanAbd; // Abdomen
    private String womanCNS; // Central Nervous System

    // Men's details
    private String manGeneralExamination;
    private String manBreastExamination;
    private String manBP; // Blood Pressure
    private String manCVC; // Cardiovascular System
    private String manRS; // Respiratory System
    private String manAbd; // Abdomen
    private String manCNS; // Central Nervous System

    private String observationsAndConclusions;
    private String references;
    private String followUps;
}
