package com.maathru.backend.Application.dto.request.eligible;


import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalHistoryDto {
    private long medicalHistoryId;

    @NotNull(message = "User is required")
    private User user;

    @NotNull(message = "Gender is required")
    private String gender;

    private boolean womenAnemia;
    private boolean womanHeartDisease;
    private boolean womanDiabetes;
    private boolean womanHighBloodPressure;
    private boolean womanHighCholesterol;
    private boolean womanChestTightnessWheezing;
    private boolean womanThyroid;
    private boolean womanDental;
    private boolean womanMentalIllness;
    private boolean womanFoodPoisoning;
    private boolean womanLongTermMedication;
    private boolean womanSurgery;
    private boolean womanOther;

    private String womanAnemiaDetails;
    private String womanHeartDiseaseDetails;
    private String womanDiabetesDetails;
    private String womanHighBloodPressureDetails;
    private String womanHighCholesterolDetails;
    private String womanChestTightnessWheezingDetails;
    private String womanThyroidDetails;
    private String womanDentalDetails;
    private String womanMentalIllnessDetails;
    private String womanFoodPoisoningDetails;
    private String womanLongTermMedicationDetails;
    private String womanSurgeryDetails;
    private String womanOtherDetails;

    private boolean menAnemia;
    private boolean menHeartDisease;
    private boolean menDiabetes;
    private boolean menHighBloodPressure;
    private boolean menHighCholesterol;
    private boolean menChestTightnessWheezing;
    private boolean menThyroid;
    private boolean menDental;
    private boolean menMentalIllness;
    private boolean menFoodPoisoning;
    private boolean menLongTermMedication;
    private boolean menSurgery;
    private boolean menOther;

    private String menAnemiaDetails;
    private String menHeartDiseaseDetails;
    private String menDiabetesDetails;
    private String menHighBloodPressureDetails;
    private String menHighCholesterolDetails;
    private String menChestTightnessWheezingDetails;
    private String menThyroidDetails;
    private String menDentalDetails;
    private String menMentalIllnessDetails;
    private String menFoodPoisoningDetails;
    private String menLongTermMedicationDetails;
    private String menSurgeryDetails;
    private String menOtherDetails;
}
