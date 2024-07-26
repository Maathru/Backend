package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import com.maathru.backend.Application.dto.eligible.MedicalHistoryDto;

public class MedicalHistoryMapper implements Mapper<MedicalHistory, MedicalHistoryDto> {

    public static MedicalHistoryDto toDto(MedicalHistory medicalHistory) {
        if (medicalHistory == null) {
            return null;
        }

        MedicalHistoryDto dto = new MedicalHistoryDto();

        dto.setWomenAnemia(medicalHistory.isWomenAnemia());
        dto.setWomanHeartDisease(medicalHistory.isWomanHeartDisease());
        dto.setWomanDiabetes(medicalHistory.isWomanDiabetes());
        dto.setWomanHighBloodPressure(medicalHistory.isWomanHighBloodPressure());
        dto.setWomanHighCholesterol(medicalHistory.isWomanHighCholesterol());
        dto.setWomanChestTightnessWheezing(medicalHistory.isWomanChestTightnessWheezing());
        dto.setWomanThyroid(medicalHistory.isWomanThyroid());
        dto.setWomanDental(medicalHistory.isWomanDental());
        dto.setWomanMentalIllness(medicalHistory.isWomanMentalIllness());
        dto.setWomanLongTermDiseases(medicalHistory.isWomanLongTermDiseases());
        dto.setWomanFoodPoisoning(medicalHistory.isWomanFoodPoisoning());
        dto.setWomanLongTermMedication(medicalHistory.isWomanLongTermMedication());
        dto.setWomanSurgery(medicalHistory.isWomanSurgery());

        dto.setManAnemia(medicalHistory.isManAnemia());
        dto.setManHeartDisease(medicalHistory.isManHeartDisease());
        dto.setManDiabetes(medicalHistory.isManDiabetes());
        dto.setManHighBloodPressure(medicalHistory.isManHighBloodPressure());
        dto.setManHighCholesterol(medicalHistory.isManHighCholesterol());
        dto.setManChestTightnessWheezing(medicalHistory.isManChestTightnessWheezing());
        dto.setManThyroid(medicalHistory.isManThyroid());
        dto.setManDental(medicalHistory.isManDental());
        dto.setManMentalIllness(medicalHistory.isManMentalIllness());
        dto.setManLongTermDiseases(medicalHistory.isManLongTermDiseases());
        dto.setManFoodPoisoning(medicalHistory.isManFoodPoisoning());
        dto.setManLongTermMedication(medicalHistory.isManLongTermMedication());
        dto.setManSurgery(medicalHistory.isManSurgery());

        dto.setAnemiaDetails(medicalHistory.getAnemiaDetails());
        dto.setHeartDiseaseDetails(medicalHistory.getHeartDiseaseDetails());
        dto.setDiabetesDetails(medicalHistory.getDiabetesDetails());
        dto.setHighBloodPressureDetails(medicalHistory.getHighBloodPressureDetails());
        dto.setHighCholesterolDetails(medicalHistory.getHighCholesterolDetails());
        dto.setChestTightnessWheezingDetails(medicalHistory.getChestTightnessWheezingDetails());
        dto.setThyroidDetails(medicalHistory.getThyroidDetails());
        dto.setDentalDetails(medicalHistory.getDentalDetails());
        dto.setMentalIllnessDetails(medicalHistory.getMentalIllnessDetails());
        dto.setLongTermDiseases(medicalHistory.getLongTermDiseases());
        dto.setFoodPoisoningDetails(medicalHistory.getFoodPoisoningDetails());
        dto.setLongTermMedicationDetails(medicalHistory.getLongTermMedicationDetails());
        dto.setSurgeryDetails(medicalHistory.getSurgeryDetails());

        return dto;
    }

    public MedicalHistory toEntity(MedicalHistory medicalHistory,MedicalHistoryDto dto) {
        if (dto == null) {
            return null;
        }

        medicalHistory.setWomenAnemia(dto.isWomenAnemia());
        medicalHistory.setWomanHeartDisease(dto.isWomanHeartDisease());
        medicalHistory.setWomanDiabetes(dto.isWomanDiabetes());
        medicalHistory.setWomanHighBloodPressure(dto.isWomanHighBloodPressure());
        medicalHistory.setWomanHighCholesterol(dto.isWomanHighCholesterol());
        medicalHistory.setWomanChestTightnessWheezing(dto.isWomanChestTightnessWheezing());
        medicalHistory.setWomanThyroid(dto.isWomanThyroid());
        medicalHistory.setWomanDental(dto.isWomanDental());
        medicalHistory.setWomanMentalIllness(dto.isWomanMentalIllness());
        medicalHistory.setWomanLongTermDiseases(dto.isWomanLongTermDiseases());
        medicalHistory.setWomanFoodPoisoning(dto.isWomanFoodPoisoning());
        medicalHistory.setWomanLongTermMedication(dto.isWomanLongTermMedication());
        medicalHistory.setWomanSurgery(dto.isWomanSurgery());

        medicalHistory.setManAnemia(dto.isManAnemia());
        medicalHistory.setManHeartDisease(dto.isManHeartDisease());
        medicalHistory.setManDiabetes(dto.isManDiabetes());
        medicalHistory.setManHighBloodPressure(dto.isManHighBloodPressure());
        medicalHistory.setManHighCholesterol(dto.isManHighCholesterol());
        medicalHistory.setManChestTightnessWheezing(dto.isManChestTightnessWheezing());
        medicalHistory.setManThyroid(dto.isManThyroid());
        medicalHistory.setManDental(dto.isManDental());
        medicalHistory.setManMentalIllness(dto.isManMentalIllness());
        medicalHistory.setManLongTermDiseases(dto.isManLongTermDiseases());
        medicalHistory.setManFoodPoisoning(dto.isManFoodPoisoning());
        medicalHistory.setManLongTermMedication(dto.isManLongTermMedication());
        medicalHistory.setManSurgery(dto.isManSurgery());

        medicalHistory.setAnemiaDetails(dto.getAnemiaDetails());
        medicalHistory.setHeartDiseaseDetails(dto.getHeartDiseaseDetails());
        medicalHistory.setDiabetesDetails(dto.getDiabetesDetails());
        medicalHistory.setHighBloodPressureDetails(dto.getHighBloodPressureDetails());
        medicalHistory.setHighCholesterolDetails(dto.getHighCholesterolDetails());
        medicalHistory.setChestTightnessWheezingDetails(dto.getChestTightnessWheezingDetails());
        medicalHistory.setThyroidDetails(dto.getThyroidDetails());
        medicalHistory.setDentalDetails(dto.getDentalDetails());
        medicalHistory.setMentalIllnessDetails(dto.getMentalIllnessDetails());
        medicalHistory.setLongTermDiseases(dto.getLongTermDiseases());
        medicalHistory.setFoodPoisoningDetails(dto.getFoodPoisoningDetails());
        medicalHistory.setLongTermMedicationDetails(dto.getLongTermMedicationDetails());
        medicalHistory.setSurgeryDetails(dto.getSurgeryDetails());

        return medicalHistory;
    }
}