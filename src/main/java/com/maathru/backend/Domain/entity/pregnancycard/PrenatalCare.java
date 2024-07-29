package com.maathru.backend.Domain.entity.pregnancycard;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "prenatal_care")
public class PrenatalCare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long prenatalCareId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pregnancy_card_id")
    private PregnancyCard pregnancyCard;

    private LocalDate dateOfArrival;
    private long weeksIntoPregnancy;

    private Boolean AttendanceMan;
    private Boolean AttendanceWoman;

    private String anemia;
    private String swelledUp;
    private String fetalHeight;
    private String fetalHeartSound;
    private String leadingPart;
    private String urineGlucose;
    private String urine;
    private String urineAlbumin;
    private String healthAdvicePrenatal;
    private String nutritionalRequirementsMyths;
    private String informedAboutBirthPeriod;
    private String informedAboutDangerousSituations;
    private String conditionsToAvoidDuringPregnancy;
    private String personalSafety;
    private String childBirth;
    private String informingMotherAboutOnsetOfLabor;
    private String whatToBringToHospital;
    private String postpartum;
    private String babyCare;
    private String breastfeedingOnly;
    private String clearingDoubt;
    private String postpartumRiskFactors;
    private String needOfFamilyDevelopment;
    private String postpartumRiskFactorsChildMother;
}
