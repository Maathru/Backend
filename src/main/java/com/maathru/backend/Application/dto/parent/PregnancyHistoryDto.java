package com.maathru.backend.Application.dto.parent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PregnancyHistoryDto {

    private int pregnanciesG;
    private int pregnanciesP;

    private int children;
    private LocalDate youngestChildBirthday;

    private LocalDate lastMenstrualPeriod;
    private LocalDate expectedDoD;
    private LocalDate usCorrectedExpectedDoD;
    private LocalDate expectedPeriodBegin;
    private LocalDate expectedPeriodEnd;
    private LocalDate firstSensationOfRotationalMotion;
    private int gestationalWeeksAtEnrollment;
    private boolean familyPlanningMethodsBeforePregnancy;
    private String familyPlanningMethodsBeforePregnancyDetails;
}
