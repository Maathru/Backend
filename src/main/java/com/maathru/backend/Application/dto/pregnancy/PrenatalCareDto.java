package com.maathru.backend.Application.dto.pregnancy;

import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PrenatalCareDto {
    @NotNull(message = "Pregnancy card should be selected")
    private long pregnancyCardId;
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
