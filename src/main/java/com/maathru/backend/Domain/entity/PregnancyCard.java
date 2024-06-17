package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pregnancy_card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PregnancyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pregnancyCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moh_id")
    private MOH moh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    private Long eligibleListId;
    private LocalDateTime lastUpdatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "last_updated_by")
    private Employee lastUpdatedBy;
    private Double bmi;
    private Double weight;
    private Double height;
    private String allergies;
    private String nameOfTheFieldClinic;
    private String nameOfHospitalClinic;
    private String nameOfTheConsultantObstetrician;
    private String identifiedAntenatalRiskConditions;
    private String morbidities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_card_id")
    private VaccineCard vaccineCard;
}