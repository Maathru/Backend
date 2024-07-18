package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.eligible.*;
import com.maathru.backend.Application.dto.request.eligible.*;
import com.maathru.backend.Domain.entity.eligible.*;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.eligible.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EligibleService {
    private final JwtService jwtService;
    private final ModelMapper mapper;
    private final BasicInfoRepository basicInfoRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final SpecialWomanRepository specialWomanRepository;
    private final SpecialBothRepository specialBothRepository;
    private final FamilyHealthInfoRepository familyHealthInfoRepository;
    private final FamilyNutritionRepository familyNutritionRepository;

    @Transactional
    public ResponseEntity<String> saveEligible(EligibleDto eligibleDto) {
        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("User not found");
        }

        BasicInfo basicInfo = basicInfoRepository.findByUser(currentUser).orElse(new BasicInfo());
        mapper.map(eligibleDto.getBasicInfoDto(), basicInfo);
        basicInfo.setUser(currentUser);

        MedicalHistory medicalHistory = medicalHistoryRepository.findByUser(currentUser).orElse(new MedicalHistory());
        mapper.map(eligibleDto.getMedicalHistoryDto(), medicalHistory);
        medicalHistory.setUser(currentUser);

        SpecialWoman specialWoman = specialWomanRepository.findByUser(currentUser).orElse(new SpecialWoman());
        mapper.map(eligibleDto.getSpecialWomanDto(), specialWoman);
        specialWoman.setUser(currentUser);

        SpecialBoth specialBoth = specialBothRepository.findByUser(currentUser).orElse(new SpecialBoth());
        mapper.map(eligibleDto.getSpecialBothDto(), specialBoth);
        specialBoth.setUser(currentUser);

        FamilyHealthInfo familyHealthInfo = familyHealthInfoRepository.findByUser(currentUser).orElse(new FamilyHealthInfo());
        mapper.map(eligibleDto.getFamilyHealthInfoDto(), familyHealthInfo);
        familyHealthInfo.setUser(currentUser);

        FamilyNutrition familyNutrition = familyNutritionRepository.findByUser(currentUser).orElse(new FamilyNutrition());
        mapper.map(eligibleDto.getFamilyNutritionDto(), familyNutrition);
        familyNutrition.setUser(currentUser);

        basicInfoRepository.save(basicInfo);
        medicalHistoryRepository.save(medicalHistory);
        specialWomanRepository.save(specialWoman);
        specialBothRepository.save(specialBoth);
        familyHealthInfoRepository.save(familyHealthInfo);
        familyNutritionRepository.save(familyNutrition);

        log.info("Eligible data added or updated successfully by {}", currentUser.getEmail());
        return ResponseEntity.status(201).body("Eligible data added or updated successfully");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EligibleDto> getEligibleData() {
        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("User not found");
        }

        BasicInfo basicInfo = basicInfoRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Basic info not found"));
        MedicalHistory medicalHistory = medicalHistoryRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Medical history not found"));
        SpecialWoman specialWoman = specialWomanRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Special woman info not found"));
        SpecialBoth specialBoth = specialBothRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Special both info not found"));
        FamilyHealthInfo familyHealthInfo = familyHealthInfoRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Family health info not found"));
        FamilyNutrition familyNutrition = familyNutritionRepository.findByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Family nutrition info not found"));

        BasicInfoDto basicInfoDto = mapper.map(basicInfo, BasicInfoDto.class);
        MedicalHistoryDto medicalHistoryDto = mapper.map(medicalHistory, MedicalHistoryDto.class);
        SpecialWomanDto specialWomanDto = mapper.map(specialWoman, SpecialWomanDto.class);
        SpecialBothDto specialBothDto = mapper.map(specialBoth, SpecialBothDto.class);
        FamilyHealthInfoDto familyHealthInfoDto = mapper.map(familyHealthInfo, FamilyHealthInfoDto.class);
        FamilyNutritionDto familyNutritionDto = mapper.map(familyNutrition, FamilyNutritionDto.class);

        EligibleDto eligibleDto = new EligibleDto();
        eligibleDto.setBasicInfoDto(basicInfoDto);
        eligibleDto.setMedicalHistoryDto(medicalHistoryDto);
        eligibleDto.setSpecialWomanDto(specialWomanDto);
        eligibleDto.setSpecialBothDto(specialBothDto);
        eligibleDto.setFamilyHealthInfoDto(familyHealthInfoDto);
        eligibleDto.setFamilyNutritionDto(familyNutritionDto);

        return ResponseEntity.status(200).body(eligibleDto);
    }
}