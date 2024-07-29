package com.maathru.backend.Application.dto.pregnancy;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentPregnancyStatusDto {
    @NotNull(message = "Pregnancy card should be selected")
    private long pregnancyCardId;
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
