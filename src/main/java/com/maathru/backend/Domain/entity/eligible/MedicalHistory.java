package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_history")
public class MedicalHistory extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean womenAnemia;
    @NotNull
    private boolean womanHeartDisease;
    @NotNull
    private boolean womanDiabetes;
    @NotNull
    private boolean womanHighBloodPressure;
    @NotNull
    private boolean womanHighCholesterol;
    @NotNull
    private boolean womanChestTightnessWheezing;
    @NotNull
    private boolean womanThyroid;
    @NotNull
    private boolean womanDental;
    @NotNull
    private boolean womanMentalIllness;
    @NotNull
    private boolean womanLongTermDiseases;
    @NotNull
    private boolean womanFoodPoisoning;
    @NotNull
    private boolean womanLongTermMedication;
    @NotNull
    private boolean womanSurgery;
    private boolean womanBloodRelatedDiseases;
    private boolean other;

    @NotNull
    private boolean manAnemia;
    @NotNull
    private boolean manHeartDisease;
    @NotNull
    private boolean manDiabetes;
    @NotNull
    private boolean manHighBloodPressure;
    @NotNull
    private boolean manHighCholesterol;
    @NotNull
    private boolean manChestTightnessWheezing;
    @NotNull
    private boolean manThyroid;
    @NotNull
    private boolean manDental;
    @NotNull
    private boolean manMentalIllness;
    @NotNull
    private boolean manLongTermDiseases;
    @NotNull
    private boolean manFoodPoisoning;
    @NotNull
    private boolean manLongTermMedication;
    @NotNull
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
    private String womanBloodRelatedDiseasesDetails;
    private String otherDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}