package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.parent.CurrentPregnancyDto;
import com.maathru.backend.Domain.entity.parent.CurrentPregnancy;
import com.maathru.backend.Domain.entity.parent.OtherSituation;

public class CurrentPregnancyMapper {
    public CurrentPregnancy toCurrentPregnancy(CurrentPregnancy currentPregnancy, CurrentPregnancyDto dto) {
        if (dto == null) {
            return null;
        }

        currentPregnancy.setPregnancyUnder_20_andOver_35(dto.isPregnancyUnder_20_andOver_35());
        currentPregnancy.setMoreThan5_pregnancies(dto.isMoreThan5_pregnancies());
        currentPregnancy.setPreeclampsia(dto.isPreeclampsia());
        currentPregnancy.setAntepartumVaginalBleeding(dto.isAntepartumVaginalBleeding());
        currentPregnancy.setMultipleBirths(dto.isMultipleBirths());
        currentPregnancy.setCasualPositions(dto.isCasualPositions());
        currentPregnancy.setDoDOfTheChildIsNotSpecified(dto.isDoDOfTheChildIsNotSpecified());
        currentPregnancy.setOtherThingsToConsider(dto.isOtherThingsToConsider());

        currentPregnancy.setPregnancyUnder_20_andOver_35Details(dto.getPregnancyUnder_20_andOver_35Details());
        currentPregnancy.setMoreThan5_pregnanciesDetails(dto.getMoreThan5_pregnanciesDetails());
        currentPregnancy.setPreeclampsiaDetails(dto.getPreeclampsiaDetails());
        currentPregnancy.setAntepartumVaginalBleedingDetails(dto.getAntepartumVaginalBleedingDetails());
        currentPregnancy.setMultipleBirthsDetails(dto.getMultipleBirthsDetails());
        currentPregnancy.setCasualPositionsDetails(dto.getCasualPositionsDetails());
        currentPregnancy.setDoDOfTheChildIsNotSpecifiedDetails(dto.getDoDOfTheChildIsNotSpecifiedDetails());
        currentPregnancy.setOtherThingsToConsiderDetails(dto.getOtherThingsToConsiderDetails());

        return currentPregnancy;
    }

    public OtherSituation toOtherSituation(OtherSituation otherSituation, CurrentPregnancyDto dto) {
        if (dto == null) {
            return null;
        }

        otherSituation.setBmiLessOrHigh(dto.isBmiLessOrHigh());
        otherSituation.setDiabetes(dto.isDiabetes());
        otherSituation.setMalaria(dto.isMalaria());
        otherSituation.setHeartDiseases(dto.isHeartDiseases());
        otherSituation.setKidneyDiseases(dto.isKidneyDiseases());

        otherSituation.setBmiLessOrHighDetails(dto.getBmiLessOrHighDetails());
        otherSituation.setDiabetesDetails(dto.getDiabetesDetails());
        otherSituation.setMalariaDetails(dto.getMalariaDetails());
        otherSituation.setHeartDiseasesDetails(dto.getHeartDiseasesDetails());
        otherSituation.setKidneyDiseasesDetails(dto.getKidneyDiseasesDetails());

        return otherSituation;
    }

    public CurrentPregnancyDto toCurrentPregnancyDto(CurrentPregnancy currentPregnancy, OtherSituation otherSituation) {
        CurrentPregnancyDto currentPregnancyDto = new CurrentPregnancyDto();

        currentPregnancyDto.setPregnancyUnder_20_andOver_35(currentPregnancy.isPregnancyUnder_20_andOver_35());
        currentPregnancyDto.setMoreThan5_pregnancies(currentPregnancy.isMoreThan5_pregnancies());
        currentPregnancyDto.setPreeclampsia(currentPregnancy.isPreeclampsia());
        currentPregnancyDto.setAntepartumVaginalBleeding(currentPregnancy.isAntepartumVaginalBleeding());
        currentPregnancyDto.setMultipleBirths(currentPregnancy.isMultipleBirths());
        currentPregnancyDto.setCasualPositions(currentPregnancy.isCasualPositions());
        currentPregnancyDto.setDoDOfTheChildIsNotSpecified(currentPregnancy.isDoDOfTheChildIsNotSpecified());
        currentPregnancyDto.setOtherThingsToConsider(currentPregnancy.isOtherThingsToConsider());

        currentPregnancyDto.setPregnancyUnder_20_andOver_35Details(currentPregnancy.getPregnancyUnder_20_andOver_35Details());
        currentPregnancyDto.setMoreThan5_pregnanciesDetails(currentPregnancy.getMoreThan5_pregnanciesDetails());
        currentPregnancyDto.setPreeclampsiaDetails(currentPregnancy.getPreeclampsiaDetails());
        currentPregnancyDto.setAntepartumVaginalBleedingDetails(currentPregnancy.getAntepartumVaginalBleedingDetails());
        currentPregnancyDto.setMultipleBirthsDetails(currentPregnancy.getMultipleBirthsDetails());
        currentPregnancyDto.setCasualPositionsDetails(currentPregnancy.getCasualPositionsDetails());
        currentPregnancyDto.setDoDOfTheChildIsNotSpecifiedDetails(currentPregnancy.getDoDOfTheChildIsNotSpecifiedDetails());
        currentPregnancyDto.setOtherThingsToConsiderDetails(currentPregnancy.getOtherThingsToConsiderDetails());

        currentPregnancyDto.setBmiLessOrHigh(otherSituation.isBmiLessOrHigh());
        currentPregnancyDto.setDiabetes(otherSituation.isDiabetes());
        currentPregnancyDto.setMalaria(otherSituation.isMalaria());
        currentPregnancyDto.setHeartDiseases(otherSituation.isHeartDiseases());
        currentPregnancyDto.setKidneyDiseases(otherSituation.isKidneyDiseases());

        currentPregnancyDto.setBmiLessOrHighDetails(otherSituation.getBmiLessOrHighDetails());
        currentPregnancyDto.setDiabetesDetails(otherSituation.getDiabetesDetails());
        currentPregnancyDto.setMalariaDetails(otherSituation.getMalariaDetails());
        currentPregnancyDto.setHeartDiseasesDetails(otherSituation.getHeartDiseasesDetails());
        currentPregnancyDto.setKidneyDiseasesDetails(otherSituation.getKidneyDiseasesDetails());

        return currentPregnancyDto;
    }
}
