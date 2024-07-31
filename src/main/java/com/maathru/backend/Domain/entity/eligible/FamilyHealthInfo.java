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
@Table(name = "family_health_info")
public class FamilyHealthInfo extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean manHighBloodPressure;
    @NotNull
    private boolean manDiabetes;
    @NotNull
    private boolean manHeartDiseases;
    @NotNull
    private boolean manNervousDisorders;
    @NotNull
    private boolean manHemophilia;
    @NotNull
    private boolean manThalassemia;
    @NotNull
    private boolean manMentalIllnessAndSuicide;
    @NotNull
    private boolean manTwins;

    @NotNull
    private boolean womanHighBloodPressure;
    @NotNull
    private boolean womanDiabetes;
    @NotNull
    private boolean womanHeartDiseases;
    @NotNull
    private boolean womanNervousDisorders;
    @NotNull
    private boolean womanHemophilia;
    @NotNull
    private boolean womanThalassemia;
    @NotNull
    private boolean womanMentalIllnessAndSuicide;
    @NotNull
    private boolean womanTwins;

    private String highBloodPressureWho;
    private String diabetesWho;
    private String heartDiseasesWho;
    private String nervousDisordersWho;
    private String hemophiliaWho;
    private String thalassemiaWho;
    private String mentalIllnessAndSuicideWho;
    private String twinsWho;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
