package com.maathru.backend.Application.dto.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentPregnancyDto {
    private boolean pregnancyUnder_20_andOver_35;
    private boolean moreThan5_pregnancies;
    private boolean preeclampsia;
    private boolean antepartumVaginalBleeding;
    private boolean multipleBirths;
    private boolean casualPositions;
    private boolean doDOfTheChildIsNotSpecified;
    private boolean otherThingsToConsider;

    private String pregnancyUnder_20_andOver_35Details;
    private String moreThan5_pregnanciesDetails;
    private String preeclampsiaDetails;
    private String antepartumVaginalBleedingDetails;
    private String multipleBirthsDetails;
    private String casualPositionsDetails;
    private String doDOfTheChildIsNotSpecifiedDetails;
    private String otherThingsToConsiderDetails;

    private boolean bmiLessOrHigh;
    private boolean diabetes;
    private boolean malaria;
    private boolean heartDiseases;
    private boolean kidneyDiseases;

    private String bmiLessOrHighDetails;
    private String diabetesDetails;
    private String malariaDetails;
    private String heartDiseasesDetails;
    private String kidneyDiseasesDetails;
}
