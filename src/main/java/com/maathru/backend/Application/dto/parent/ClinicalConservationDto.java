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

}
