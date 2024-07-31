package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.eligible.EligibleCoupleDTO;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Application.dto.eligible.BasicInfoDto;
import com.maathru.backend.enumeration.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BasicInfoMapper implements Mapper<BasicInfo, BasicInfoDto> {

    public static BasicInfoDto toDto(BasicInfo basicInfo) {
        if (basicInfo == null) {
            return null;
        }

        BasicInfoDto dto = new BasicInfoDto();
        dto.setManAge(basicInfo.getManAge());
        dto.setWomanAge(basicInfo.getWomanAge());
        dto.setManEducationLevel(basicInfo.getManEducationLevel());
        dto.setWomanEducationLevel(basicInfo.getWomanEducationLevel());
        dto.setManOccupation(basicInfo.getManOccupation());
        dto.setWomanOccupation(basicInfo.getWomanOccupation());
        dto.setMarriedDate(basicInfo.getMarriedDate());
        dto.setWomanName(basicInfo.getWomanName());
        dto.setManName(basicInfo.getManName());
        dto.setAddress(basicInfo.getAddress());
        dto.setUserId(basicInfo.getUser().getUserId());
        dto.setCreatedDate(LocalDate.from(basicInfo.getCreatedAt()));

        return dto;
    }

    public static List<EligibleCoupleResponse> toParentResponseList(List<BasicInfo> basicInfoList) {
        List<EligibleCoupleResponse> eligibleCoupleResponseList = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfoList) {
            // Skip entries with role ELIGIBLE
            if (basicInfo.getUser().getRole() == Role.ELIGIBLE) {
                continue;
            }
            EligibleCoupleResponse response = toEligibleCoupleResponse(basicInfo);
            eligibleCoupleResponseList.add(response);
        }

        return eligibleCoupleResponseList;
    }

    public BasicInfo toEntity(BasicInfo basicInfo, BasicInfoDto dto) {
        if (dto == null) {
            return null;
        }

        basicInfo.setManAge(dto.getManAge());
        basicInfo.setWomanAge(dto.getWomanAge());
        basicInfo.setManEducationLevel(dto.getManEducationLevel());
        basicInfo.setWomanEducationLevel(dto.getWomanEducationLevel());
        basicInfo.setManOccupation(dto.getManOccupation());
        basicInfo.setWomanOccupation(dto.getWomanOccupation());

        basicInfo.setMarriedDate(dto.getMarriedDate().plusDays(1));

        return basicInfo;
    }

    public static BasicInfo toEntityByMidwife(BasicInfo basicInfo, EligibleCoupleDTO dto) {
        if (dto == null) {
            return null;
        }

        basicInfo.setWomanName(dto.getWomanName());
        basicInfo.setManName(dto.getManName());
        basicInfo.setAddress(dto.getAddress());
        basicInfo.setWomanPhone(dto.getWomanPhone());
        basicInfo.setManPhone(dto.getManPhone());
        basicInfo.setWomanDob(dto.getWomanDob());
        basicInfo.setManDob(dto.getManDob());
        basicInfo.setWomanAgeMarried(dto.getWomanAgeMarried());
        basicInfo.setManAgeMarried(dto.getManAgeMarried());
        basicInfo.setWomanEducationLevel(dto.getWomanEducationLevel());
        basicInfo.setManEducationLevel(dto.getManEducationLevel());
        basicInfo.setWomanOccupation(dto.getWomanOccupation());
        basicInfo.setManOccupation(dto.getManOccupation());
        basicInfo.setChildren(dto.getChildren());

        return basicInfo;
    }

    public static EligibleCoupleResponse toEligibleCoupleResponse(BasicInfo basicInfo) {
        EligibleCoupleResponse eligibleCoupleResponse = new EligibleCoupleResponse();

        eligibleCoupleResponse.setId(basicInfo.getId());
        eligibleCoupleResponse.setWomanName(basicInfo.getWomanName());
        eligibleCoupleResponse.setManName(basicInfo.getManName());
        eligibleCoupleResponse.setAddress(basicInfo.getAddress());
        eligibleCoupleResponse.setWomanPhone(basicInfo.getWomanPhone());
        eligibleCoupleResponse.setManPhone(basicInfo.getManPhone());
        eligibleCoupleResponse.setWomanDob(basicInfo.getWomanDob());
        eligibleCoupleResponse.setManDob(basicInfo.getManDob());
        eligibleCoupleResponse.setChildren(basicInfo.getChildren());
        eligibleCoupleResponse.setRole(basicInfo.getUser().getRole());
        eligibleCoupleResponse.setUserId(basicInfo.getUser().getUserId());

        return eligibleCoupleResponse;
    }

    public static List<EligibleCoupleResponse> toEligibleCoupleResponseList(List<BasicInfo> basicInfos) {
        List<EligibleCoupleResponse> eligibleCoupleResponseList = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfos) {
            EligibleCoupleResponse response = toEligibleCoupleResponse(basicInfo);
            eligibleCoupleResponseList.add(response);
        }

        return eligibleCoupleResponseList;
    }
}