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
    @Column(name = "pregnancy_card_id", nullable = false)
    private Long pregnancyCardId;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "moh_id")
    private MOH moh;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "eligible_list_id")
    private Long eligibleListId;

    @Column(name = "last_updated_at", nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "height")
    private Double height;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "name_of_the_field_clinic")
    private String nameOfTheFieldClinic;

    @Column(name = "name_of_hospital_clinic")
    private String nameOfHospitalClinic;

    @Column(name = "name_of_the_consultant_obstetrician")
    private String nameOfTheConsultantObstetrician;

    @Column(name = "identified_antenatal_risk_condition")
    private String identifiedAntenatalRiskConditions;

    @Column(name = "morbidities")
    private String morbidities;
}
