package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalHistoryDto {

    @NotNull(message = "Woman's Low Red Blood cells (Anemia) field value cannot be empty")
    private boolean womenAnemia;
    @NotNull(message = "Woman's Heart disease or rheumatism from birth field cannot be empty")
    private boolean womanHeartDisease;
    @NotNull(message = "Woman's Diabetics field cannot be empty")
    private boolean womanDiabetes;
    @NotNull(message = "Woman's High Blood Pressure field cannot be empty")
    private boolean womanHighBloodPressure;
    @NotNull(message = "Woman's High Blood Cholesterol level field cannot be empty")
    private boolean womanHighCholesterol;
    @NotNull(message = "Woman's Chest Tightness Wheezing field cannot be empty")
    private boolean womanChestTightnessWheezing;
    @NotNull(message = "Woman's Thyroid related Diseases field cannot be empty")
    private boolean womanThyroid;
    @NotNull(message = "Woman's Dental problems field cannot be empty")
    private boolean womanDental;
    @NotNull(message = "Woman's Mental illness field cannot be empty")
    private boolean womanMentalIllness;
    @NotNull(message = "Woman's Diseases with Long-term complication field cannot be empty")
    private boolean womanLongTermDiseases;
    @NotNull(message = "Woman's Food poisoning field cannot be empty")
    private boolean womanFoodPoisoning;
    @NotNull(message = "Woman's Use of long term medication field cannot be empty")
    private boolean womanLongTermMedication;
    @NotNull(message = "Woman's Any other Major surgeries field cannot be empty")
    private boolean womanSurgery;

    @NotNull(message = "Man's Low Red Blood cells (Anemia) field value cannot be empty")
    private boolean manAnemia;
    @NotNull(message = "Man's Heart disease or rheumatism from birth field value cannot be empty")
    private boolean manHeartDisease;
    @NotNull(message = "Man's Diabetics field value cannot be empty")
    private boolean manDiabetes;
    @NotNull(message = "Man's High Blood Pressure field value cannot be empty")
    private boolean manHighBloodPressure;
    @NotNull(message = "Man's High Blood Cholesterol field value cannot be empty")
    private boolean manHighCholesterol;
    @NotNull(message = "Man's Chest Tightness Wheezing field value cannot be empty")
    private boolean manChestTightnessWheezing;
    @NotNull(message = "Man's Thyroid related Diseases field value cannot be empty")
    private boolean manThyroid;
    @NotNull(message = "Man's Dental problems field value cannot be empty")
    private boolean manDental;
    @NotNull(message = "Man's Mental illness field value cannot be empty")
    private boolean manMentalIllness;
    @NotNull(message = "Man's Diseases with Long-term complication field value cannot be empty")
    private boolean manLongTermDiseases;
    @NotNull(message = "Man's Food poisoning field value cannot be empty")
    private boolean manFoodPoisoning;
    @NotNull(message = "Man's Use of long term medication field value cannot be empty")
    private boolean manLongTermMedication;
    @NotNull(message = "Man's Any other Major surgeries field value cannot be empty")
    private boolean manSurgery;

    private String anemiaDetails;
    private String heartDiseaseDetails;
    private String diabetesDetails;
    private String highBloodPressureDetails;
    private String highCholesterolDetails;
    private String chestTightnessWheezingDetails;
    private String thyroidDetails;
    private String dentalDetails;
    private String mentalIllnessDetails;
    private String longTermDiseases;
    private String foodPoisoningDetails;
    private String longTermMedicationDetails;
    private String surgeryDetails;

}
