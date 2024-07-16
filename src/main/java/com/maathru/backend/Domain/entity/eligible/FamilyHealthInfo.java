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
@Table(name = "family_health_info")
public class FamilyHealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long familyHealthInfoId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Gender gender;

    private boolean highBloodPressure;
    private String highBloodPressureWho;

    private boolean diabetes;
    private String diabetesWho;

    private boolean heartDiseases;
    private String heartDiseasesWho;

    private boolean nervousDisorders;
    private String nervousDisordersWho;

    private boolean hemophilia;
    private String hemophiliaWho;

    private boolean thalassemia;
    private String thalassemiaWho;

    private boolean mentalIllnessAndSuicide;
    private String mentalIllnessAndSuicideWho;

    private boolean twins;
    private String twinsWho;
}
