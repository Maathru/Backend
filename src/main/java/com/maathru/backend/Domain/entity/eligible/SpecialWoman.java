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
@Table(name = "special_woman")
public class SpecialWoman extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean rubella;
    @NotNull
    private boolean folicAcid;
    @NotNull
    private boolean consanguineous;
    @NotNull
    private boolean periodsPattern;
    @NotNull
    private String period;
    @NotNull
    private boolean heavyBleed;
    @NotNull
    private boolean vaginalBleed;
    @NotNull
    private boolean abdominalPain;
    @NotNull
    private boolean vaginalDischarge;
    @NotNull
    private boolean abortion;
    @NotNull
    private boolean infantMortality;
    @NotNull
    private boolean stillbirth;
    @NotNull
    private boolean tubalPregnancy;

    private String rubellaDetails;
    private String folicAcidDetails;
    private String consanguineousDetails;
    private String periodsPatternDetails;
    private String heavyBleedOther;
    private String vaginalBleedOther;
    private String abdominalPainOther;
    private String vaginalDischargeOther;
    private String abortionOther;
    private String infantMortalityOther;
    private String stillbirthOther;
    private String tubalPregnancyOther;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
