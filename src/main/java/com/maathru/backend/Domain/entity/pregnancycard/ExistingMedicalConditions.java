package com.maathru.backend.Domain.entity.pregnancycard;

import com.maathru.backend.Domain.entity.Parent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "existing_medical_conditions")
public class ExistingMedicalConditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long existingMedicalConditionsId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private Boolean bloodRelatives;
    private Boolean rubella;
    private Boolean pregnancyScreening;
    private Boolean folicAcid;
    private Boolean infertility;

    private String bloodRelativesDetails;
    private String rubellaDetails;
    private String pregnancyScreeningDetails;
    private String folicAcidDetails;
    private String infertilityDetails;
}