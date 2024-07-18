package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialWomanDto {
    @NotNull(message = "Have you received the rubella vaccine? filed value cannot be empty")
    private boolean rubella;
    @NotNull(message = "Do you take folic acid pills daily? filed value cannot be empty")
    private boolean folicAcid;
    @NotNull(message = "Was the marriage between the two of you a consanguineous relationship? filed value cannot be empty")
    private boolean consanguineous;
    @NotNull(message = "Are your periods monthly? filed value cannot be empty")
    private boolean periodsPattern;
    @NotNull(message = "Orderly/Irregular filed value cannot be empty")
    private String period;
    @NotNull(message = "Do you bleed heavily when you have your period? filed value cannot be empty")
    private boolean heavyBleed;
    @NotNull(message = "Do you have vaginal bleeding between two periods? filed value cannot be empty")
    private boolean vaginalBleed;
    @NotNull(message = "Suffering from severe back pain during menstruation? filed value cannot be empty")
    private boolean abdominalPain;
    @NotNull(message = "Do you have an unusual colored or foul-smelling vaginal discharge? filed value cannot be empty")
    private boolean vaginalDischarge;
    @NotNull(message = "Abortions filed value cannot be empty")
    private boolean abortion;
    @NotNull(message = "Infant mortality filed value cannot be empty")
    private boolean infantMortality;
    @NotNull(message = "Stillbirths filed value cannot be empty")
    private boolean stillbirth;
    @NotNull(message = "Tubal Pregnancy (Ectopic) filed value cannot be empty")
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
