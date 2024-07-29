package com.maathru.backend.Domain.entity.pregnancycard;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "present_obstetric_history")
public class PresentObstetricHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long presentObstetricHistoryId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pregnancy_card_id")
    private PregnancyCard pregnancyCard;

    private int noOfPregnanciesG;
    private int noOfPregnanciesP;
    private int noOfLivingChildren;

    private LocalDate youngestChildDOB;
    private LocalDate lastMenstrualPeriod;
    private LocalDate expectedDeliveryDate;
    private LocalDate expectedDeliveryDateByUltrasound;
    private LocalDate expectedPeriodBegin;
    private LocalDate expectedPeriodEnd;
    private LocalDate firstSensation;
}