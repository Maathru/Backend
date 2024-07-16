package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_condition_women")
public class MedicalConditionWomen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long medicalConditionsForWomenId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean rubella;
    private String rubellaDetails;

    private boolean folic;
    private String folicDetails;

    private boolean consanguinity;
    private String consanguinityDetails;
}