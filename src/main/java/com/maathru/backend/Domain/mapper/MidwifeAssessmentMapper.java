package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.eligible.EligibleCoupleDTO;
import com.maathru.backend.Domain.entity.eligible.MidwifeAssessment;

public class MidwifeAssessmentMapper {
    public static MidwifeAssessment toEntityByMidwife(MidwifeAssessment midwifeAssessment, EligibleCoupleDTO dto) {
        if (dto == null) {
            return null;
        }

        midwifeAssessment.setWomanWeight(dto.getWomanWeight());
        midwifeAssessment.setManWeight(dto.getManWeight());
        midwifeAssessment.setWomanHeight(dto.getWomanHeight());
        midwifeAssessment.setManHeight(dto.getManHeight());
        midwifeAssessment.setWomanBmi(dto.getWomanBmi());
        midwifeAssessment.setManBmi(dto.getManBmi());
        midwifeAssessment.setWomanBloodType(dto.getWomanBloodType());
        midwifeAssessment.setManBloodType(dto.getManBloodType());
        midwifeAssessment.setWomanHemoglobin(dto.getWomanHemoglobin());
        midwifeAssessment.setManHemoglobin(dto.getManHemoglobin());
        midwifeAssessment.setSpecial(dto.getSpecial());
        midwifeAssessment.setSession(dto.getSession());

        return midwifeAssessment;
    }
}
