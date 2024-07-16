package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long medicalHistoryId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Gender gender;

    private boolean anemia;
    private boolean heartDisease;
    private boolean diabetes;
    private boolean highBloodPressure;
    private boolean highCholesterol;
    private boolean chestTightnessWheezing;
    private boolean thyroid;
    private boolean dental;
    private boolean mentalIllness;
    private boolean foodPoisoning;
    private boolean longTermMedication;
    private boolean surgery;
    private boolean other;

    private String anemiaDetails;
    private String heartDiseaseDetails;
    private String diabetesDetails;
    private String highBloodPressureDetails;
    private String highCholesterolDetails;
    private String chestTightnessWheezingDetails;
    private String thyroidDetails;
    private String dentalDetails;
    private String mentalIllnessDetails;
    private String foodPoisoningDetails;
    private String longTermMedicationDetails;
    private String surgeryDetails;
    private String otherDetails;

}