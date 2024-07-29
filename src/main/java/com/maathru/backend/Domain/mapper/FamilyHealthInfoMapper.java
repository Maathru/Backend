package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Domain.entity.eligible.FamilyHealthInfo;
import com.maathru.backend.Application.dto.eligible.FamilyHealthInfoDto;

public class FamilyHealthInfoMapper implements Mapper<FamilyHealthInfo, FamilyHealthInfoDto> {

    public static FamilyHealthInfoDto toDto(FamilyHealthInfo familyHealthInfo) {
        if (familyHealthInfo == null) {
            return null;
        }

        FamilyHealthInfoDto dto = new FamilyHealthInfoDto();

        dto.setManHighBloodPressure(familyHealthInfo.isManHighBloodPressure());
        dto.setManDiabetes(familyHealthInfo.isManDiabetes());
        dto.setManHeartDiseases(familyHealthInfo.isManHeartDiseases());
        dto.setManNervousDisorders(familyHealthInfo.isManNervousDisorders());
        dto.setManHemophilia(familyHealthInfo.isManHemophilia());
        dto.setManThalassemia(familyHealthInfo.isManThalassemia());
        dto.setManMentalIllnessAndSuicide(familyHealthInfo.isManMentalIllnessAndSuicide());
        dto.setManTwins(familyHealthInfo.isManTwins());

        dto.setWomanHighBloodPressure(familyHealthInfo.isWomanHighBloodPressure());
        dto.setWomanDiabetes(familyHealthInfo.isWomanDiabetes());
        dto.setWomanHeartDiseases(familyHealthInfo.isWomanHeartDiseases());
        dto.setWomanNervousDisorders(familyHealthInfo.isWomanNervousDisorders());
        dto.setWomanHemophilia(familyHealthInfo.isWomanHemophilia());
        dto.setWomanThalassemia(familyHealthInfo.isWomanThalassemia());
        dto.setWomanMentalIllnessAndSuicide(familyHealthInfo.isWomanMentalIllnessAndSuicide());
        dto.setWomanTwins(familyHealthInfo.isWomanTwins());

        dto.setHighBloodPressureWho(familyHealthInfo.getHighBloodPressureWho());
        dto.setDiabetesWho(familyHealthInfo.getDiabetesWho());
        dto.setHeartDiseasesWho(familyHealthInfo.getHeartDiseasesWho());
        dto.setNervousDisordersWho(familyHealthInfo.getNervousDisordersWho());
        dto.setHemophiliaWho(familyHealthInfo.getHemophiliaWho());
        dto.setThalassemiaWho(familyHealthInfo.getThalassemiaWho());
        dto.setMentalIllnessAndSuicideWho(familyHealthInfo.getMentalIllnessAndSuicideWho());
        dto.setTwinsWho(familyHealthInfo.getTwinsWho());

        return dto;
    }

    public FamilyHealthInfo toEntity(FamilyHealthInfo familyHealthInfo,FamilyHealthInfoDto dto) {
        if (dto == null) {
            return null;
        }

        familyHealthInfo.setManHighBloodPressure(dto.isManHighBloodPressure());
        familyHealthInfo.setManDiabetes(dto.isManDiabetes());
        familyHealthInfo.setManHeartDiseases(dto.isManHeartDiseases());
        familyHealthInfo.setManNervousDisorders(dto.isManNervousDisorders());
        familyHealthInfo.setManHemophilia(dto.isManHemophilia());
        familyHealthInfo.setManThalassemia(dto.isManThalassemia());
        familyHealthInfo.setManMentalIllnessAndSuicide(dto.isManMentalIllnessAndSuicide());
        familyHealthInfo.setManTwins(dto.isManTwins());

        familyHealthInfo.setWomanHighBloodPressure(dto.isWomanHighBloodPressure());
        familyHealthInfo.setWomanDiabetes(dto.isWomanDiabetes());
        familyHealthInfo.setWomanHeartDiseases(dto.isWomanHeartDiseases());
        familyHealthInfo.setWomanNervousDisorders(dto.isWomanNervousDisorders());
        familyHealthInfo.setWomanHemophilia(dto.isWomanHemophilia());
        familyHealthInfo.setWomanThalassemia(dto.isWomanThalassemia());
        familyHealthInfo.setWomanMentalIllnessAndSuicide(dto.isWomanMentalIllnessAndSuicide());
        familyHealthInfo.setWomanTwins(dto.isWomanTwins());

        familyHealthInfo.setHighBloodPressureWho(dto.getHighBloodPressureWho());
        familyHealthInfo.setDiabetesWho(dto.getDiabetesWho());
        familyHealthInfo.setHeartDiseasesWho(dto.getHeartDiseasesWho());
        familyHealthInfo.setNervousDisordersWho(dto.getNervousDisordersWho());
        familyHealthInfo.setHemophiliaWho(dto.getHemophiliaWho());
        familyHealthInfo.setThalassemiaWho(dto.getThalassemiaWho());
        familyHealthInfo.setMentalIllnessAndSuicideWho(dto.getMentalIllnessAndSuicideWho());
        familyHealthInfo.setTwinsWho(dto.getTwinsWho());

        return familyHealthInfo;
    }
}

