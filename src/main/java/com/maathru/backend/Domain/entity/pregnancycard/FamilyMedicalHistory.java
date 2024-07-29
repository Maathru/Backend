package com.maathru.backend.Domain.entity.pregnancycard;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "family_medical_history")
public class FamilyMedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long familyMedicalHistoryId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pregnancy_card_id")
    private PregnancyCard pregnancyCard;

    private Boolean diabetes;
    private Boolean hbp;
    private Boolean bloodDiseases;
    private Boolean others;

    private String diabetesDetails;
    private String hbpDetails;
    private String bloodDiseasesDetails;
    private String othersDetails;
}