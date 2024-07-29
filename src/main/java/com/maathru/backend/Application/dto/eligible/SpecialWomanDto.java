package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialWomanDto {
    @NotNull(message = "Have you received the rubella vaccine? field value cannot be empty")
    private boolean rubella;
    @NotNull(message = "Do you take folic acid pills daily? field value cannot be empty")
    private boolean folicAcid;
    @NotNull(message = "Was the marriage between the two of you a consanguineous relationship? field value cannot be empty")
    private boolean consanguineous;
    @NotNull(message = "Are your periods monthly? field value cannot be empty")
    private boolean periodsPattern;
    @NotEmpty(message = "Orderly/Irregular field value cannot be empty")
    private String period;
    @NotNull(message = "Do you bleed heavily when you have your period? field value cannot be empty")
    private boolean heavyBleed;
    @NotNull(message = "Do you have vaginal bleeding between two periods? field value cannot be empty")
    private boolean vaginalBleed;
    @NotNull(message = "Suffering from severe back pain during menstruation? field value cannot be empty")
    private boolean abdominalPain;
    @NotNull(message = "Do you have an unusual colored or foul-smelling vaginal discharge? field value cannot be empty")
    private boolean vaginalDischarge;
    @NotNull(message = "Abortions field value cannot be empty")
    private boolean abortion;
    @NotNull(message = "Infant mortality field value cannot be empty")
    private boolean infantMortality;
    @NotNull(message = "Stillbirths field value cannot be empty")
    private boolean stillbirth;
    @NotNull(message = "Tubal Pregnancy (Ectopic) field value cannot be empty")
    private boolean tubalPregnancy;

    private String rubellaDetails;
    private String folicAcidDetails;
    private String consanguineousDetails;
    private String periodsPatternDetails;
    private String heavyBleedOther;
    private String vaginalBleedOther;
    private String abdominalPainOther;
    private String vaginalDischargeOther;
    private String abortionOther;
    private String infantMortalityOther;
    private String stillbirthOther;
    private String tubalPregnancyOther;
}
