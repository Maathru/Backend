package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.parent.ParentDetailsDto;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Domain.entity.parent.PreExistingMedicalCondition;

public class ParentDetailsMapper {
    public BasicInfo toBasicInfo(BasicInfo basicInfo, ParentDetailsDto dto) {
        if (dto == null) {
            return null;
        }

        basicInfo.setWomanName(dto.getWomanName());
        basicInfo.setManName(dto.getManName());

        basicInfo.setAddress(dto.getAddress());
        basicInfo.setLocation(dto.getLocation());

        basicInfo.setWomanPhone(dto.getWomanPhone());
        basicInfo.setManPhone(dto.getManPhone());

        basicInfo.setWomanDob(dto.getWomanDob());
        basicInfo.setManDob(dto.getManDob());

        basicInfo.setWomanAgeMarried(dto.getWomanAgeMarried());
        basicInfo.setManAgeMarried(dto.getManAgeMarried());

        basicInfo.setWomanOccupation(dto.getWomanOccupation());
        basicInfo.setManOccupation(dto.getManOccupation());

        basicInfo.setDistance(dto.getDistance());

        return basicInfo;
    }

    public PreExistingMedicalCondition toPreExistingMedicalCondition(PreExistingMedicalCondition preExistingMedicalCondition, ParentDetailsDto dto) {
        if (dto == null) {
            return null;
        }

        preExistingMedicalCondition.setBloodRelatives(dto.isBloodRelatives());
        preExistingMedicalCondition.setBloodRelativesDetails(dto.getBloodRelativesDetails());

        preExistingMedicalCondition.setRubella(dto.isRubella());
        preExistingMedicalCondition.setRubellaDetails(dto.getRubellaDetails());

        preExistingMedicalCondition.setPregnancyScreening(dto.isPregnancyScreening());
        preExistingMedicalCondition.setPregnancyScreeningDetails(dto.getPregnancyScreeningDetails());

        preExistingMedicalCondition.setFolicAcid(dto.isFolicAcid());
        preExistingMedicalCondition.setFolicAcidDetails(dto.getFolicAcidDetails());

        preExistingMedicalCondition.setInfertility(dto.isInfertility());
        preExistingMedicalCondition.setInfertilityDetails(dto.getInfertilityDetails());

        return preExistingMedicalCondition;
    }

    public ParentDetailsDto toParentDetailsDto(BasicInfo basicInfo, PreExistingMedicalCondition preExistingMedicalCondition) {
        ParentDetailsDto parentDetailsDto = new ParentDetailsDto();

        parentDetailsDto.setWomanName(basicInfo.getWomanName());
        parentDetailsDto.setManName(basicInfo.getManName());

        parentDetailsDto.setAddress(basicInfo.getAddress());
        parentDetailsDto.setLocation(basicInfo.getLocation());

        parentDetailsDto.setWomanPhone(basicInfo.getWomanPhone());
        parentDetailsDto.setManPhone(basicInfo.getManPhone());

        parentDetailsDto.setWomanDob(basicInfo.getWomanDob());
        parentDetailsDto.setManDob(basicInfo.getManDob());

        parentDetailsDto.setWomanAgeMarried(basicInfo.getWomanAgeMarried());
        parentDetailsDto.setManAgeMarried(basicInfo.getManAgeMarried());

        parentDetailsDto.setWomanEducationLevel(basicInfo.getWomanEducationLevel());
        parentDetailsDto.setManEducationLevel(basicInfo.getManEducationLevel());

        parentDetailsDto.setWomanOccupation(basicInfo.getWomanOccupation());
        parentDetailsDto.setManOccupation(basicInfo.getManOccupation());

        parentDetailsDto.setDistance(basicInfo.getDistance());

        parentDetailsDto.setBloodRelatives(preExistingMedicalCondition.isBloodRelatives());
        parentDetailsDto.setBloodRelativesDetails(preExistingMedicalCondition.getBloodRelativesDetails());

        parentDetailsDto.setRubella(preExistingMedicalCondition.isRubella());
        parentDetailsDto.setRubellaDetails(preExistingMedicalCondition.getRubellaDetails());

        parentDetailsDto.setPregnancyScreening(preExistingMedicalCondition.isPregnancyScreening());
        parentDetailsDto.setPregnancyScreeningDetails(preExistingMedicalCondition.getPregnancyScreeningDetails());

        parentDetailsDto.setFolicAcid(preExistingMedicalCondition.isFolicAcid());
        parentDetailsDto.setFolicAcidDetails(preExistingMedicalCondition.getFolicAcidDetails());

        parentDetailsDto.setInfertility(preExistingMedicalCondition.isInfertility());
        parentDetailsDto.setInfertilityDetails(preExistingMedicalCondition.getInfertilityDetails());

        return parentDetailsDto;
    }
}
