package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pre_existing_medical_conditions")
public class PreExistingMedicalCondition extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean bloodRelatives;
    @NotNull
    private boolean rubella;
    @NotNull
    private boolean pregnancyScreening;
    @NotNull
    private boolean folicAcid;
    @NotNull
    private boolean infertility;

    private String bloodRelativesDetails;
    private String rubellaDetails;
    private String pregnancyScreeningDetails;
    private String folicAcidDetails;
    private String infertilityDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
