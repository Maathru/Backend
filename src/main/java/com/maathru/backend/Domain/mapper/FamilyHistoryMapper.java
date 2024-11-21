package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.parent.FamilyHistoryDto;
import com.maathru.backend.Domain.entity.eligible.MedicalHistory;

public class FamilyHistoryMapper {
    public MedicalHistory toMedicalHistory(MedicalHistory medicalHistory, FamilyHistoryDto dto) {
        if (dto == null) {
            return null;
        }

        medicalHistory.setWomanDiabetes(dto.isWomanDiabetes());
        medicalHistory.setWomanHighBloodPressure(dto.isWomanHighBloodPressure());
        medicalHistory.setWomanBloodRelatedDiseases(dto.isWomanBloodRelatedDiseases());
        medicalHistory.setOther(dto.isOther());

        medicalHistory.setDiabetesDetails(dto.getDiabetesDetails());
        medicalHistory.setHighBloodPressureDetails(dto.getHighBloodPressureDetails());
        medicalHistory.setWomanBloodRelatedDiseasesDetails(dto.getWomanBloodRelatedDiseasesDetails());
        medicalHistory.setOtherDetails(dto.getOtherDetails());

        return medicalHistory;
    }

    public FamilyHistoryDto toFamilyHistoryDto(MedicalHistory medicalHistory) {
        FamilyHistoryDto familyHistoryDto = new FamilyHistoryDto();

        familyHistoryDto.setWomanDiabetes(medicalHistory.isWomanDiabetes());
        familyHistoryDto.setWomanHighBloodPressure(medicalHistory.isWomanHighBloodPressure());
        familyHistoryDto.setWomanBloodRelatedDiseases(medicalHistory.isWomanBloodRelatedDiseases());
        familyHistoryDto.setOther(medicalHistory.isOther());

        familyHistoryDto.setDiabetesDetails(medicalHistory.getDiabetesDetails());
        familyHistoryDto.setHighBloodPressureDetails(medicalHistory.getHighBloodPressureDetails());
        familyHistoryDto.setWomanBloodRelatedDiseasesDetails(medicalHistory.getWomanBloodRelatedDiseasesDetails());
        familyHistoryDto.setOtherDetails(medicalHistory.getOtherDetails());

        return familyHistoryDto;
    }
}
