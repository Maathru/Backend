package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.eligible.*;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.entity.FamilyPlanningMethod;
import com.maathru.backend.Domain.entity.PastPregnancy;
import com.maathru.backend.Domain.entity.eligible.*;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.mapper.*;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.External.repository.eligible.*;
import com.maathru.backend.enumeration.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EligibleService {
    private final JwtService jwtService;
    private final ModelMapper mapper;
    private final BasicInfoRepository basicInfoRepository;
    private final MidwifeAssessmentRepository midwifeAssessmentRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final SpecialWomanRepository specialWomanRepository;
    private final SpecialBothRepository specialBothRepository;
    private final FamilyHealthInfoRepository familyHealthInfoRepository;
    private final FamilyNutritionRepository familyNutritionRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public ResponseEntity<String> saveOrUpdateEligible(EligibleDto eligibleDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = setDataForEntity(
                    basicInfoRepository.findByUser(currentUser).orElseGet(BasicInfo::new),
                    currentUser,
                    new BasicInfoMapper(),
                    eligibleDto.getBasicInfoDto()
            );

            MedicalHistory medicalHistory = setDataForEntity(
                    medicalHistoryRepository.findByUser(currentUser).orElseGet(MedicalHistory::new),
                    currentUser,
                    new MedicalHistoryMapper(),
                    eligibleDto.getMedicalHistoryDto()
            );

            SpecialWoman specialWoman = setDataForEntity(
                    specialWomanRepository.findByUser(currentUser).orElseGet(SpecialWoman::new),
                    currentUser,
                    new SpecialWomanMapper(),
                    eligibleDto.getSpecialWomanDto()
            );

            SpecialBoth specialBoth = setDataForEntity(
                    specialBothRepository.findByUser(currentUser).orElseGet(SpecialBoth::new),
                    currentUser,
                    new SpecialBothMapper(),
                    eligibleDto.getSpecialBothDto()
            );

            FamilyHealthInfo familyHealthInfo = setDataForEntity(
                    familyHealthInfoRepository.findByUser(currentUser).orElseGet(FamilyHealthInfo::new),
                    currentUser,
                    new FamilyHealthInfoMapper(),
                    eligibleDto.getFamilyHealthInfoDto()
            );

            FamilyNutrition familyNutrition = setDataForEntity(
                    familyNutritionRepository.findByUser(currentUser).orElseGet(FamilyNutrition::new),
                    currentUser,
                    new FamilyNutritionMapper(),
                    eligibleDto.getFamilyNutritionDto()
            );

            // Save entities
            basicInfoRepository.save(basicInfo);
            medicalHistoryRepository.save(medicalHistory);
            specialWomanRepository.save(specialWoman);
            specialBothRepository.save(specialBoth);
            familyHealthInfoRepository.save(familyHealthInfo);
            familyNutritionRepository.save(familyNutrition);

            log.info("Eligible data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Eligible data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving eligible data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving eligible data");
        }
    }

    private <T extends BaseEntity, D> T setDataForEntity(T entity, User currentUser, Mapper<T, D> mapper, D dto) {
        if (entity.getId() == 0) {
            entity.setCreatedBy(currentUser);
        }
        mapper.toEntity(entity, dto);
        entity.setUser(currentUser);
        entity.setUpdatedBy(currentUser);
        return entity;
    }


    @Transactional(readOnly = true)
    public ResponseEntity<EligibleDto> getEligibleData() {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUser(currentUser).orElseGet(BasicInfo::new);
            MedicalHistory medicalHistory = medicalHistoryRepository.findByUser(currentUser).orElseGet(MedicalHistory::new);
            SpecialWoman specialWoman = specialWomanRepository.findByUser(currentUser).orElseGet(SpecialWoman::new);
            SpecialBoth specialBoth = specialBothRepository.findByUser(currentUser).orElseGet(SpecialBoth::new);
            FamilyHealthInfo familyHealthInfo = familyHealthInfoRepository.findByUser(currentUser).orElseGet(FamilyHealthInfo::new);
            FamilyNutrition familyNutrition = familyNutritionRepository.findByUser(currentUser).orElseGet(FamilyNutrition::new);
            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUser(currentUser).orElseGet(MidwifeAssessment::new);

            BasicInfoDto basicInfoDto = mapper.map(basicInfo, BasicInfoDto.class);
            basicInfoDto.setUserId(basicInfo.getUser().getUserId());
            basicInfoDto.setCreatedDate(basicInfo.getCreatedAt().toLocalDate());

            String region = getRegionNameByUser(basicInfo.getCreatedBy());
            Object[] obj = getMOHDetailsByUser(basicInfo.getCreatedBy());
//            log.info("Region: {}", region);
//            log.info("Area {}",obj);
            basicInfoDto.setRegion(region);
            basicInfoDto.setMoh(obj[0]);



            EligibleDto eligibleDto = new EligibleDto();
            eligibleDto.setBasicInfoDto(basicInfoDto);
            eligibleDto.setMedicalHistoryDto(mapper.map(medicalHistory, MedicalHistoryDto.class));
            eligibleDto.setSpecialWomanDto(mapper.map(specialWoman, SpecialWomanDto.class));
            eligibleDto.setSpecialBothDto(mapper.map(specialBoth, SpecialBothDto.class));
            eligibleDto.setFamilyHealthInfoDto(mapper.map(familyHealthInfo, FamilyHealthInfoDto.class));
            eligibleDto.setFamilyNutritionDto(mapper.map(familyNutrition, FamilyNutritionDto.class));
            eligibleDto.setMidwifeAssessmentDto(mapper.map(midwifeAssessment, MidwifeAssessmentDto.class));

            return ResponseEntity.status(HttpStatus.OK).body(eligibleDto);
        } catch (Exception e) {
            log.error("Error retrieving eligible data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // create eligible user by midwife
    @Transactional
    public ResponseEntity<String> createOrUpdateEligibleCouple(EligibleCoupleDTO eligibleCoupleDTO) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User eligibleUser = userRepository.findByEmail(eligibleCoupleDTO.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));

            BasicInfo basicInfo = basicInfoRepository.findByUser(eligibleUser).orElseGet(BasicInfo::new);
            mapBasicInfo(eligibleCoupleDTO, basicInfo, currentUser, eligibleUser);

            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUser(eligibleUser).orElseGet(MidwifeAssessment::new);
            mapMidwifeAssessment(eligibleCoupleDTO, midwifeAssessment, currentUser, eligibleUser);

            basicInfoRepository.save(basicInfo);
            midwifeAssessmentRepository.save(midwifeAssessment);

            log.info("New eligible couple created/updated successfully for user: {} by:{}", eligibleUser.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Eligible couple created or updated successfully");
        } catch (NotFoundException e) {
            log.error("Not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating/updating eligible couple for user: {}", eligibleCoupleDTO.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating/updating eligible couple");
        }
    }

    private void mapBasicInfo(EligibleCoupleDTO dto, BasicInfo basicInfo, User byUser, User eligibleUser) {
        if (basicInfo.getId() == 0) {
            basicInfo.setCreatedBy(byUser);
        }
        mapper.map(dto, basicInfo);
        basicInfo.setUser(eligibleUser);
        basicInfo.setUpdatedBy(byUser);
        basicInfo.setPastPregnancies(mapPastPregnancies(dto.getPastPregnancies(), basicInfo, byUser));
        basicInfo.setFamilyPlanningMethods(mapFamilyPlanningMethods(dto.getFamilyPlanningMethods(), basicInfo, byUser));
    }

    private List<PastPregnancy> mapPastPregnancies(List<PastPregnancyDTO> dtos, BasicInfo basicInfo, User byUser) {
        return dtos.stream().map(dto -> {
            PastPregnancy pp = new PastPregnancy();
            pp.setGender(Gender.valueOf(dto.getGender()));
            pp.setResult(dto.getResult());
            pp.setBasicInfo(basicInfo);
            pp.setCreatedBy(byUser);
            pp.setUpdatedBy(byUser);
            return pp;
        }).collect(Collectors.toList());
    }

    private List<FamilyPlanningMethod> mapFamilyPlanningMethods(List<FamilyPlanningMethodDTO> dtos, BasicInfo basicInfo, User byUser) {
        return dtos.stream().map(dto -> {
            FamilyPlanningMethod fpm = new FamilyPlanningMethod();
            fpm.setDate(dto.getDate());
            fpm.setMethod(dto.getMethod());
            fpm.setBasicInfo(basicInfo);
            fpm.setCreatedBy(byUser);
            fpm.setUpdatedBy(byUser);
            return fpm;
        }).collect(Collectors.toList());
    }

    private void mapMidwifeAssessment(EligibleCoupleDTO dto, MidwifeAssessment midwifeAssessment, User byUser, User eligibleUser) {
        if (midwifeAssessment.getId() == 0) {
            midwifeAssessment.setCreatedBy(byUser);
        }
        mapper.map(dto, midwifeAssessment);
        midwifeAssessment.setUser(eligibleUser);
        midwifeAssessment.setUpdatedBy(byUser);
    }

    // get eligible data for Midwife
    @Transactional(readOnly = true)
    public ResponseEntity<EligibleCoupleDTO> getEligibleForMidwife(String email) {
        try {
            User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));

            BasicInfo basicInfo = basicInfoRepository.findByUser(currentUser).orElseGet(BasicInfo::new);
            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUser(currentUser).orElseGet(MidwifeAssessment::new);

            List<PastPregnancyDTO> pastPregnancyDTOs = basicInfo.getPastPregnancies().stream()
                    .map(this::convertToPastPregnancyDTO)
                    .collect(Collectors.toList());

            List<FamilyPlanningMethodDTO> familyPlanningMethodDTOs = basicInfo.getFamilyPlanningMethods().stream()
                    .map(this::convertToFamilyPlanningMethodDTO)
                    .collect(Collectors.toList());

            EligibleCoupleDTO eligibleCoupleDTO = new EligibleCoupleDTO();
            mapper.map(basicInfo, eligibleCoupleDTO);
            mapper.map(midwifeAssessment, eligibleCoupleDTO);
            eligibleCoupleDTO.setPastPregnancies(pastPregnancyDTOs);
            eligibleCoupleDTO.setFamilyPlanningMethods(familyPlanningMethodDTOs);

            return ResponseEntity.status(HttpStatus.OK).body(eligibleCoupleDTO);
        } catch (NotFoundException e) {
            log.error("Not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error retrieving eligible couple data for user: {} {}", email, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private PastPregnancyDTO convertToPastPregnancyDTO(PastPregnancy pastPregnancy) {
        PastPregnancyDTO pastPregnancyDTO = new PastPregnancyDTO();
        pastPregnancyDTO.setGender(String.valueOf(pastPregnancy.getGender()));
        pastPregnancyDTO.setResult(pastPregnancy.getResult());
        return pastPregnancyDTO;
    }

    private FamilyPlanningMethodDTO convertToFamilyPlanningMethodDTO(FamilyPlanningMethod familyPlanningMethod) {
        FamilyPlanningMethodDTO familyPlanningMethodDTO = new FamilyPlanningMethodDTO();
        familyPlanningMethodDTO.setDate(familyPlanningMethod.getDate());
        familyPlanningMethodDTO.setMethod(familyPlanningMethod.getMethod());
        return familyPlanningMethodDTO;
    }

    public ResponseEntity<List<EligibleCoupleResponse>> getEligibleListForMidwife() {
        try {
            List<BasicInfo> basicInfoList = basicInfoRepository.findAll();
            if (basicInfoList.isEmpty()) {
                throw new NotFoundException("Eligible users not found");
            }

            List<EligibleCoupleResponse> eligibleCoupleResponseList = BasicInfoMapper.toEligibleCoupleResponseList(basicInfoList);
            return ResponseEntity.ok(eligibleCoupleResponseList);
        } catch (Exception e) {
            log.error("Error retrieving eligible couples data {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public String getRegionNameByUser(User user) {
        return employeeRepository.getRegionName(user);
    }

    public Object[] getMOHDetailsByUser(User user) {
        return employeeRepository.getAreaAndDistrict(user);
//        return new MOHDetailsDTO((Area) result[0], (District) result[1]);
    }
}
