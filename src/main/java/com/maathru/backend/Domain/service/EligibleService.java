package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.BasicInfoDto;
import com.maathru.backend.Domain.entity.BasicInfo;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.BasicInfoRepository;
import com.maathru.backend.enumeration.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EligibleService {
    private final JwtService jwtService;
    private final BasicInfoRepository basicInfoRepository;

    public ResponseEntity<String> saveBasicInfo(BasicInfoDto basicInfoDto) {
        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("User not found");
        }

        // man details
        BasicInfo man = new BasicInfo();
        man.setAge(basicInfoDto.getManAge());
        man.setEducationLevel(basicInfoDto.getManEducationLevel());
        man.setOccupation(basicInfoDto.getManOccupation());
        man.setMarriedDate(basicInfoDto.getMarriedDate());
        man.setGender(Gender.MALE);
        man.setCreatedBy(currentUser);
        man.setUpdatedBy(currentUser);
        basicInfoRepository.save(man);
        log.info("Man's basic info added successful by {}:{}", currentUser.getRole(), currentUser.getEmail());

        // woman details
        BasicInfo woman = new BasicInfo();
        woman.setAge(basicInfoDto.getWomanAge());
        woman.setEducationLevel(basicInfoDto.getWomanEducationLevel());
        woman.setOccupation(basicInfoDto.getWomanOccupation());
        woman.setMarriedDate(basicInfoDto.getMarriedDate());
        woman.setGender(Gender.MALE);
        woman.setCreatedBy(currentUser);
        woman.setUpdatedBy(currentUser);
        basicInfoRepository.save(woman);
        log.info("Woman's basic info added successful by {}:{}", currentUser.getRole(), currentUser.getEmail());

        return ResponseEntity.status(201).body("Basic info added successfully");
    }
}
