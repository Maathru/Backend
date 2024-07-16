package com.maathru.backend.Application.dto.request.eligible;

import com.maathru.backend.Domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyHealthInfoDto {
    private long familyHealthInfoId;

    @NotNull(message = "User is required")
    private User user;

    private boolean menHighBloodPressure;
    private String menHighBloodPressureWho;

    private boolean menDiabetes;
    private String menDiabetesWho;

    private boolean menHeartDiseases;
    private String menHeartDiseasesWho;

    private boolean menNervousDisorders;
    private String menNervousDisordersWho;

    private boolean menHemophilia;
    private String menHemophiliaWho;

    private boolean menThalassemia;
    private String menThalassemiaWho;

    private boolean menMentalIllnessAndSuicide;
    private String menMentalIllnessAndSuicideWho;

    private boolean menTwins;
    private String menTwinsWho;

    private boolean womenHighBloodPressure;
    private String womenHighBloodPressureWho;

    private boolean womenDiabetes;
    private String womenDiabetesWho;

    private boolean womenHeartDiseases;
    private String womenHeartDiseasesWho;

    private boolean womenNervousDisorders;
    private String womenNervousDisordersWho;

    private boolean womenHemophilia;
    private String womenHemophiliaWho;

    private boolean womenThalassemia;
    private String womenThalassemiaWho;

    private boolean womenMentalIllnessAndSuicide;
    private String womenMentalIllnessAndSuicideWho;

    private boolean womenTwins;
    private String womenTwinsWho;
}
