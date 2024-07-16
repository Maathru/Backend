package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.BasicInfoDto;
import com.maathru.backend.Application.dto.request.eligible.MedicalHistoryDto;
import com.maathru.backend.Domain.entity.BasicInfo;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.BasicInfoRepository;
import com.maathru.backend.External.repository.MedicalHistoryRepository;
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
    private final MedicalHistoryRepository medicalHistoryRepository;

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

    public ResponseEntity<String> saveMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("User not found");
        }

        // woman details
        MedicalHistory medicalHistoryFemale = new MedicalHistory();

        medicalHistoryFemale.setUser(currentUser);
        medicalHistoryFemale.setGender(Gender.FEMALE);
        medicalHistoryFemale.setAnemia(medicalHistoryDto.isWomenAnemia());
        medicalHistoryFemale.setHeartDisease(medicalHistoryDto.isWomanHeartDisease());
        medicalHistoryFemale.setDiabetes(medicalHistoryDto.isWomanDiabetes());
        medicalHistoryFemale.setHighBloodPressure(medicalHistoryDto.isWomanHighBloodPressure());
        medicalHistoryFemale.setHighCholesterol(medicalHistoryDto.isWomanHighCholesterol());
        medicalHistoryFemale.setChestTightnessWheezing(medicalHistoryDto.isWomanChestTightnessWheezing());
        medicalHistoryFemale.setThyroid(medicalHistoryDto.isWomanThyroid());
        medicalHistoryFemale.setDental(medicalHistoryDto.isWomanDental());
        medicalHistoryFemale.setMentalIllness(medicalHistoryDto.isWomanMentalIllness());
        medicalHistoryFemale.setFoodPoisoning(medicalHistoryDto.isWomanFoodPoisoning());
        medicalHistoryFemale.setSurgery(medicalHistoryDto.isWomanSurgery());
        medicalHistoryFemale.setOther(medicalHistoryDto.isWomanOther());

        medicalHistoryFemale.setAnemiaDetails(medicalHistoryDto.getWomanAnemiaDetails());
        medicalHistoryFemale.setHeartDiseaseDetails(medicalHistoryDto.getWomanHeartDiseaseDetails());
        medicalHistoryFemale.setDiabetesDetails(medicalHistoryDto.getWomanDiabetesDetails());
        medicalHistoryFemale.setHighBloodPressureDetails(medicalHistoryDto.getWomanHighBloodPressureDetails());
        medicalHistoryFemale.setHighCholesterolDetails(medicalHistoryDto.getWomanHighCholesterolDetails());
        medicalHistoryFemale.setChestTightnessWheezingDetails(medicalHistoryDto.getWomanChestTightnessWheezingDetails());
        medicalHistoryFemale.setThyroidDetails(medicalHistoryDto.getWomanThyroidDetails());
        medicalHistoryFemale.setDentalDetails(medicalHistoryDto.getWomanDentalDetails());
        medicalHistoryFemale.setMentalIllnessDetails(medicalHistoryDto.getWomanMentalIllnessDetails());
        medicalHistoryFemale.setFoodPoisoningDetails(medicalHistoryDto.getWomanFoodPoisoningDetails());
        medicalHistoryFemale.setSurgeryDetails(medicalHistoryDto.getWomanSurgeryDetails());
        medicalHistoryFemale.setOtherDetails(medicalHistoryDto.getWomanOtherDetails());

        medicalHistoryRepository.save(medicalHistoryFemale);

        log.info("Woman's medical history added successful by {}:{}", currentUser.getRole(), currentUser.getEmail());

        // men details
        MedicalHistory medicalHistoryMale = new MedicalHistory();

        medicalHistoryMale.setUser(currentUser);
        medicalHistoryMale.setGender(Gender.MALE);
        medicalHistoryMale.setAnemia(medicalHistoryDto.isMenAnemia());
        medicalHistoryMale.setHeartDisease(medicalHistoryDto.isMenHeartDisease());
        medicalHistoryMale.setDiabetes(medicalHistoryDto.isMenDiabetes());
        medicalHistoryMale.setHighBloodPressure(medicalHistoryDto.isMenHighBloodPressure());
        medicalHistoryMale.setHighCholesterol(medicalHistoryDto.isMenHighCholesterol());
        medicalHistoryMale.setChestTightnessWheezing(medicalHistoryDto.isMenChestTightnessWheezing());
        medicalHistoryMale.setThyroid(medicalHistoryDto.isMenThyroid());
        medicalHistoryMale.setDental(medicalHistoryDto.isMenDental());
        medicalHistoryMale.setMentalIllness(medicalHistoryDto.isMenMentalIllness());
        medicalHistoryMale.setFoodPoisoning(medicalHistoryDto.isMenFoodPoisoning());
        medicalHistoryMale.setSurgery(medicalHistoryDto.isMenSurgery());
        medicalHistoryMale.setOther(medicalHistoryDto.isMenOther());

        medicalHistoryMale.setAnemiaDetails(medicalHistoryDto.getMenAnemiaDetails());
        medicalHistoryMale.setHeartDiseaseDetails(medicalHistoryDto.getMenHeartDiseaseDetails());
        medicalHistoryMale.setDiabetesDetails(medicalHistoryDto.getMenDiabetesDetails());
        medicalHistoryMale.setHighBloodPressureDetails(medicalHistoryDto.getMenHighBloodPressureDetails());
        medicalHistoryMale.setHighCholesterolDetails(medicalHistoryDto.getMenHighCholesterolDetails());
        medicalHistoryMale.setChestTightnessWheezingDetails(medicalHistoryDto.getMenChestTightnessWheezingDetails());
        medicalHistoryMale.setThyroidDetails(medicalHistoryDto.getMenThyroidDetails());
        medicalHistoryMale.setDentalDetails(medicalHistoryDto.getMenDentalDetails());
        medicalHistoryMale.setMentalIllnessDetails(medicalHistoryDto.getMenMentalIllnessDetails());
        medicalHistoryMale.setFoodPoisoningDetails(medicalHistoryDto.getMenFoodPoisoningDetails());
        medicalHistoryMale.setSurgeryDetails(medicalHistoryDto.getMenSurgeryDetails());
        medicalHistoryMale.setOtherDetails(medicalHistoryDto.getMenOtherDetails());

        medicalHistoryRepository.save(medicalHistoryMale);

        log.info("Man's medical history added successful by {}:{}", currentUser.getRole(), currentUser.getEmail());

        return ResponseEntity.status(201).body("Medical history added successfully");
    }
}