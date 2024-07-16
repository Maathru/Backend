package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sexual_health")
public class SexualHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long sexualHealthId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Gender gender;

    // Women's health details
    private boolean monthlyPeriods;
    private int numberOfDays;
    private String periodRegularity; // "Orderly" or "Irregular"

    private boolean heavyBleeding;
    private String heavyBleedingDetails;

    private boolean vaginalBleedingBetweenPeriods;
    private String vaginalBleedingDetails;

    private boolean severeBackPainDuringMenstruation;
    private String backPainDetails;

    // Questions for both
    private boolean dissatisfiedWithSex;
    private String dissatisfiedWithSexDetails;

    private boolean familyPlanningSystem;
    private String familyPlanningSystemDetails;

    private boolean delayFirstChild;
    private String delayFirstChildDuration;

    // for woman

    private boolean breastSelfExamination;
    private String breastSelfExaminationDetails;
}