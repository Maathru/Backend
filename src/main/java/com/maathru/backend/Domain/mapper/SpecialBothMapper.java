package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Domain.entity.eligible.SpecialBoth;
import com.maathru.backend.Application.dto.eligible.SpecialBothDto;

public class SpecialBothMapper implements Mapper<SpecialBoth, SpecialBothDto> {

    public static SpecialBothDto toDto(SpecialBoth specialBoth) {
        if (specialBoth == null) {
            return null;
        }

        SpecialBothDto dto = new SpecialBothDto();

        dto.setManDissatisfiedSex(specialBoth.isManDissatisfiedSex());
        dto.setManFamilyPlaning(specialBoth.isManFamilyPlaning());
        dto.setManDelayFirstBirth(specialBoth.isManDelayFirstBirth());
        dto.setWomanDissatisfiedSex(specialBoth.isWomanDissatisfiedSex());
        dto.setWomanFamilyPlaning(specialBoth.isWomanFamilyPlaning());
        dto.setWomanDelayFirstBirth(specialBoth.isWomanDelayFirstBirth());
        dto.setManBreastExamination(specialBoth.isManBreastExamination());

        dto.setDissatisfiedSexDetails(specialBoth.getDissatisfiedSexDetails());
        dto.setFamilyPlaningDetails(specialBoth.getFamilyPlaningDetails());
        dto.setDelayFirstBirthDetails(specialBoth.getDelayFirstBirthDetails());
        dto.setBreastExaminationDetails(specialBoth.getBreastExaminationDetails());

        return dto;
    }

    public SpecialBoth toEntity(SpecialBoth specialBoth,SpecialBothDto dto) {
        if (dto == null) {
            return null;
        }

        specialBoth.setManDissatisfiedSex(dto.isManDissatisfiedSex());
        specialBoth.setManFamilyPlaning(dto.isManFamilyPlaning());
        specialBoth.setManDelayFirstBirth(dto.isManDelayFirstBirth());
        specialBoth.setWomanDissatisfiedSex(dto.isWomanDissatisfiedSex());
        specialBoth.setWomanFamilyPlaning(dto.isWomanFamilyPlaning());
        specialBoth.setWomanDelayFirstBirth(dto.isWomanDelayFirstBirth());
        specialBoth.setManBreastExamination(dto.isManBreastExamination());

        specialBoth.setDissatisfiedSexDetails(dto.getDissatisfiedSexDetails());
        specialBoth.setFamilyPlaningDetails(dto.getFamilyPlaningDetails());
        specialBoth.setDelayFirstBirthDetails(dto.getDelayFirstBirthDetails());
        specialBoth.setBreastExaminationDetails(dto.getBreastExaminationDetails());

        return specialBoth;
    }
}
