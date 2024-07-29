package com.maathru.backend.Domain.entity.pregnancycard;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "current_pregnancy_status")
public class CurrentPregnancyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long currentPregnancyStatusId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pregnancy_card_id")
    private PregnancyCard pregnancyCard;

    private Boolean pregnancyBelow20Above35;
    private Boolean moreThan5Pregnancies;
    private Boolean preeclempsia;
    private Boolean vaginalBleeding;
    private Boolean multipleBirths;
    private Boolean casualPositions;
    private Boolean deliveryDateNotSpecified;
    private Boolean other;

    private Boolean bmiMoreOrLess;
    private Boolean diabetes;
    private Boolean malaria;
    private Boolean heartDiseases;
    private Boolean kidneyDiseases;

    private String pregnancyBelow20Above35Details;
    private String moreThan5PregnanciesDetails;
    private String preeclempsiaDetails;
    private String vaginalBleedingDetails;
    private String multipleBirthsDetails;
    private String casualPositionsDetails;
    private String deliveryDateNotSpecifiedDetails;
    private String otherDetails;

    private String bmiMoreOrLessDetails;
    private String diabetesDetails;
    private String malariaDetails;
    private String heartDiseasesDetails;
    private String kidneyDiseasesDetails;
}