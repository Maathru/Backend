package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Application.dto.eligible.BasicInfoDto;

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