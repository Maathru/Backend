package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyHealthInfoDto {
    @NotNull(message = "Man's High blood pressure field value cannot be empty")
    private boolean manHighBloodPressure;
    @NotNull(message = "Man's Diabetes field value cannot be empty")
    private boolean manDiabetes;
    @NotNull(message = "Man's Heart Diseases field value cannot be empty")
    private boolean manHeartDiseases;
    @NotNull(message = "Man's Nervous disorders field value cannot be empty")
    private boolean manNervousDisorders;
    @NotNull(message = "Man's Hemophilia field value cannot be empty")
    private boolean manHemophilia;
    @NotNull(message = "Man's Thalassemia field value cannot be empty")
    private boolean manThalassemia;
    @NotNull(message = "Man's A history of mental illness and suicide field value cannot be empty")
    private boolean manMentalIllnessAndSuicide;
    @NotNull(message = "Man's Twins field value cannot be empty")
    private boolean manTwins;

    @NotNull(message = "Woman's High blood pressure field value cannot be empty")
    private boolean womanHighBloodPressure;
    @NotNull(message = "Woman's Diabetes field value cannot be empty")
    private boolean womanDiabetes;
    @NotNull(message = "Woman's Heart Diseases field value cannot be empty")
    private boolean womanHeartDiseases;
    @NotNull(message = "Woman's Nervous disorders field value cannot be empty")
    private boolean womanNervousDisorders;
    @NotNull(message = "Woman's Hemophilia field value cannot be empty")
    private boolean womanHemophilia;
    @NotNull(message = "Woman's Thalassemia field value cannot be empty")
    private boolean womanThalassemia;
    @NotNull(message = "Woman's A history of mental illness and suicide field value cannot be empty")
    private boolean womanMentalIllnessAndSuicide;
    @NotNull(message = "Woman's Twins field value cannot be empty")
    private boolean womanTwins;

    private String highBloodPressureWho;
    private String diabetesWho;
    private String heartDiseasesWho;
    private String nervousDisordersWho;
    private String hemophiliaWho;
    private String thalassemiaWho;
    private String mentalIllnessAndSuicideWho;
    private String twinsWho;
}
