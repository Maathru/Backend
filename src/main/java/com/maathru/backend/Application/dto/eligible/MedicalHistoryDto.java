package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalHistoryDto {

    @NotNull(message = "Woman's Low Red Blood cells (Anemia) filed value cannot be empty")
    private boolean womenAnemia;
    @NotNull(message = "Woman's Heart disease or rheumatism from birth filed cannot be empty")
    private boolean womanHeartDisease;
    @NotNull(message = "Woman's Diabetics filed cannot be empty")
    private boolean womanDiabetes;
    @NotNull(message = "Woman's High Blood Pressure filed cannot be empty")
    private boolean womanHighBloodPressure;
    @NotNull(message = "Woman's High Blood Cholesterol level filed cannot be empty")
    private boolean womanHighCholesterol;
    @NotNull(message = "Woman's Chest Tightness Wheezing filed cannot be empty")
    private boolean womanChestTightnessWheezing;
    @NotNull(message = "Woman's Thyroid related Diseases filed cannot be empty")
    private boolean womanThyroid;
    @NotNull(message = "Woman's Dental problems filed cannot be empty")
    private boolean womanDental;
    @NotNull(message = "Woman's Mental illness filed cannot be empty")
    private boolean womanMentalIllness;
    @NotNull(message = "Woman's Diseases with Long-term complication filed cannot be empty")
    private boolean womanLongTermDiseases;
    @NotNull(message = "Woman's Food poisoning filed cannot be empty")
    private boolean womanFoodPoisoning;
    @NotNull(message = "Woman's Use of long term medication filed cannot be empty")
    private boolean womanLongTermMedication;
    @NotNull(message = "Woman's Any other Major surgeries filed cannot be empty")
    private boolean womanSurgery;

    @NotNull(message = "Man's Low Red Blood cells (Anemia) filed value cannot be empty")
    private boolean manAnemia;
    @NotNull(message = "Man's Heart disease or rheumatism from birth filed value cannot be empty")
    private boolean manHeartDisease;
    @NotNull(message = "Man's Diabetics filed value cannot be empty")
    private boolean manDiabetes;
    @NotNull(message = "Man's High Blood Pressure filed value cannot be empty")
    private boolean manHighBloodPressure;
    @NotNull(message = "Man's High Blood Cholesterol filed value cannot be empty")
    private boolean manHighCholesterol;
    @NotNull(message = "Man's Chest Tightness Wheezing filed value cannot be empty")
    private boolean manChestTightnessWheezing;
    @NotNull(message = "Man's Thyroid related Diseases filed value cannot be empty")
    private boolean manThyroid;
    @NotNull(message = "Man's Dental problems filed value cannot be empty")
    private boolean manDental;
    @NotNull(message = "Man's Mental illness filed value cannot be empty")
    private boolean manMentalIllness;
    @NotNull(message = "Man's Diseases with Long-term complication filed value cannot be empty")
    private boolean manLongTermDiseases;
    @NotNull(message = "Man's Food poisoning filed value cannot be empty")
    private boolean manFoodPoisoning;
    @NotNull(message = "Man's Use of long term medication filed value cannot be empty")
    private boolean manLongTermMedication;
    @NotNull(message = "Man's Any other Major surgeries filed value cannot be empty")
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
