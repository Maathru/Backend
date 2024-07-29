package com.maathru.backend.Application.dto.pregnancy;

import com.maathru.backend.Domain.entity.PregnancyCard;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PresentObstetricHistoryDto {
    @NotNull(message = "Pregnancy card should be selected")
    private long pregnancyCardId;

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
