package com.maathru.backend.Application.dto.parent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClinicalConservationDto {
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
}
