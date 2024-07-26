package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Domain.entity.eligible.SpecialWoman;
import com.maathru.backend.Application.dto.eligible.SpecialWomanDto;

public class SpecialWomanMapper implements Mapper<SpecialWoman, SpecialWomanDto> {

    public static SpecialWomanDto toDto(SpecialWoman specialWoman) {
        if (specialWoman == null) {
            return null;
        }

        SpecialWomanDto dto = new SpecialWomanDto();

        dto.setRubella(specialWoman.isRubella());
        dto.setFolicAcid(specialWoman.isFolicAcid());
        dto.setConsanguineous(specialWoman.isConsanguineous());
        dto.setPeriodsPattern(specialWoman.isPeriodsPattern());
        dto.setPeriod(specialWoman.getPeriod());
        dto.setHeavyBleed(specialWoman.isHeavyBleed());
        dto.setVaginalBleed(specialWoman.isVaginalBleed());
        dto.setAbdominalPain(specialWoman.isAbdominalPain());
        dto.setVaginalDischarge(specialWoman.isVaginalDischarge());
        dto.setAbortion(specialWoman.isAbortion());
        dto.setInfantMortality(specialWoman.isInfantMortality());
        dto.setStillbirth(specialWoman.isStillbirth());
        dto.setTubalPregnancy(specialWoman.isTubalPregnancy());

        dto.setRubellaDetails(specialWoman.getRubellaDetails());
        dto.setFolicAcidDetails(specialWoman.getFolicAcidDetails());
        dto.setConsanguineousDetails(specialWoman.getConsanguineousDetails());
        dto.setPeriodsPatternDetails(specialWoman.getPeriodsPatternDetails());
        dto.setHeavyBleedOther(specialWoman.getHeavyBleedOther());
        dto.setVaginalBleedOther(specialWoman.getVaginalBleedOther());
        dto.setAbdominalPainOther(specialWoman.getAbdominalPainOther());
        dto.setVaginalDischargeOther(specialWoman.getVaginalDischargeOther());
        dto.setAbortionOther(specialWoman.getAbortionOther());
        dto.setInfantMortalityOther(specialWoman.getInfantMortalityOther());
        dto.setStillbirthOther(specialWoman.getStillbirthOther());
        dto.setTubalPregnancyOther(specialWoman.getTubalPregnancyOther());

        return dto;
    }

    public SpecialWoman toEntity(SpecialWoman specialWoman,SpecialWomanDto dto) {
        if (dto == null) {
            return null;
        }

        specialWoman.setRubella(dto.isRubella());
        specialWoman.setFolicAcid(dto.isFolicAcid());
        specialWoman.setConsanguineous(dto.isConsanguineous());
        specialWoman.setPeriodsPattern(dto.isPeriodsPattern());
        specialWoman.setPeriod(dto.getPeriod());
        specialWoman.setHeavyBleed(dto.isHeavyBleed());
        specialWoman.setVaginalBleed(dto.isVaginalBleed());
        specialWoman.setAbdominalPain(dto.isAbdominalPain());
        specialWoman.setVaginalDischarge(dto.isVaginalDischarge());
        specialWoman.setAbortion(dto.isAbortion());
        specialWoman.setInfantMortality(dto.isInfantMortality());
        specialWoman.setStillbirth(dto.isStillbirth());
        specialWoman.setTubalPregnancy(dto.isTubalPregnancy());

        specialWoman.setRubellaDetails(dto.getRubellaDetails());
        specialWoman.setFolicAcidDetails(dto.getFolicAcidDetails());
        specialWoman.setConsanguineousDetails(dto.getConsanguineousDetails());
        specialWoman.setPeriodsPatternDetails(dto.getPeriodsPatternDetails());
        specialWoman.setHeavyBleedOther(dto.getHeavyBleedOther());
        specialWoman.setVaginalBleedOther(dto.getVaginalBleedOther());
        specialWoman.setAbdominalPainOther(dto.getAbdominalPainOther());
        specialWoman.setVaginalDischargeOther(dto.getVaginalDischargeOther());
        specialWoman.setAbortionOther(dto.getAbortionOther());
        specialWoman.setInfantMortalityOther(dto.getInfantMortalityOther());
        specialWoman.setStillbirthOther(dto.getStillbirthOther());
        specialWoman.setTubalPregnancyOther(dto.getTubalPregnancyOther());

        return specialWoman;
    }
}