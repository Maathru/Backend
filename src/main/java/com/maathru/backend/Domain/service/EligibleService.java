package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.eligible.*;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.FamilyPlanningMethod;
import com.maathru.backend.Domain.entity.PastPregnancy;
import com.maathru.backend.Domain.entity.eligible.*;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.InvalidException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.mapper.*;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.External.repository.eligible.*;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final ParentHabitRepository parentHabitRepository;
    private final HomeEnvironmentRepository homeEnvironmentRepository;

    @Transactional
    public ResponseEntity<String> saveOrUpdateEligible(EligibleDto eligibleDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = setDataForEntity(
                    basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new),
                    currentUser,
                    new BasicInfoMapper(),
                    eligibleDto.getBasicInfoDto()
            );

            MedicalHistory medicalHistory = setDataForEntity(
                    medicalHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(MedicalHistory::new),
                    currentUser,
                    new MedicalHistoryMapper(),
                    eligibleDto.getMedicalHistoryDto()
            );

            SpecialWoman specialWoman = setDataForEntity(
                    specialWomanRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(SpecialWoman::new),
                    currentUser,
                    new SpecialWomanMapper(),
                    eligibleDto.getSpecialWomanDto()
            );

            SpecialBoth specialBoth = setDataForEntity(
                    specialBothRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(SpecialBoth::new),
                    currentUser,
                    new SpecialBothMapper(),
                    eligibleDto.getSpecialBothDto()
            );

            FamilyHealthInfo familyHealthInfo = setDataForEntity(
                    familyHealthInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(FamilyHealthInfo::new),
                    currentUser,
                    new FamilyHealthInfoMapper(),
                    eligibleDto.getFamilyHealthInfoDto()
            );

            FamilyNutrition familyNutrition = setDataForEntity(
                    familyNutritionRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(FamilyNutrition::new),
                    currentUser,
                    new FamilyNutritionMapper(),
                    eligibleDto.getFamilyNutritionDto()
            );

            ParentHabit parentHabit = setDataForEntity(
                    parentHabitRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ParentHabit::new),
                    currentUser,
                    new ParentHabitMapper(),
                    eligibleDto.getParentHabitDto()
            );

            HomeEnvironment homeEnvironment = setDataForEntity(
                    homeEnvironmentRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(HomeEnvironment::new),
                    currentUser,
                    new HomeEnvironmentMapper(),
                    eligibleDto.getHomeEnvironmentDto()
            );

            // Save entities
            basicInfoRepository.save(basicInfo);
            medicalHistoryRepository.save(medicalHistory);
            specialWomanRepository.save(specialWoman);
            specialBothRepository.save(specialBoth);
            familyHealthInfoRepository.save(familyHealthInfo);
            familyNutritionRepository.save(familyNutrition);
            parentHabitRepository.save(parentHabit);
            homeEnvironmentRepository.save(homeEnvironment);

            log.info("Eligible data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Eligible data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving eligible data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving eligible data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EligibleDto> getEligibleData() {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new);
            MedicalHistory medicalHistory = medicalHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(MedicalHistory::new);
            SpecialWoman specialWoman = specialWomanRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(SpecialWoman::new);
            SpecialBoth specialBoth = specialBothRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(SpecialBoth::new);
            FamilyHealthInfo familyHealthInfo = familyHealthInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(FamilyHealthInfo::new);
            FamilyNutrition familyNutrition = familyNutritionRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(FamilyNutrition::new);
            ParentHabit parentHabit = parentHabitRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ParentHabit::new);
            HomeEnvironment homeEnvironment = homeEnvironmentRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(HomeEnvironment::new);
            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(MidwifeAssessment::new);

            BasicInfoDto basicInfoDto = BasicInfoMapper.toDto(basicInfo);
            basicInfoDto.setUserId(basicInfo.getUser().getUserId());
            basicInfoDto.setCreatedDate(basicInfo.getCreatedAt().toLocalDate());

            // get moh by midwife
            Object[] obj = getMOHDetailsByUser(basicInfo.getCreatedBy());
            basicInfoDto.setMoh(obj[0]);

            EligibleDto eligibleDto = new EligibleDto();
            eligibleDto.setBasicInfoDto(basicInfoDto);
            eligibleDto.setMedicalHistoryDto(mapper.map(medicalHistory, MedicalHistoryDto.class));
            eligibleDto.setSpecialWomanDto(mapper.map(specialWoman, SpecialWomanDto.class));
            eligibleDto.setSpecialBothDto(mapper.map(specialBoth, SpecialBothDto.class));
            eligibleDto.setFamilyHealthInfoDto(mapper.map(familyHealthInfo, FamilyHealthInfoDto.class));
            eligibleDto.setFamilyNutritionDto(mapper.map(familyNutrition, FamilyNutritionDto.class));
            eligibleDto.setParentHabitDto(mapper.map(parentHabit, ParentHabitDto.class));
            eligibleDto.setHomeEnvironmentDto(mapper.map(homeEnvironment, HomeEnvironmentDto.class));
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
            User eligibleUser = userRepository.findById(eligibleCoupleDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));

            if (eligibleUser.getRole() == Role.ADMIN || eligibleUser.getRole() == Role.DOCTOR || eligibleUser.getRole() == Role.MIDWIFE) {
                throw new InvalidException("This user cannot handle eligible user profile");
            }

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(eligibleUser).orElseGet(BasicInfo::new);
            mapBasicInfo(eligibleCoupleDTO, basicInfo, currentUser, eligibleUser);

            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUserAndDeletedAtIsNull(eligibleUser).orElseGet(MidwifeAssessment::new);
            mapMidwifeAssessment(eligibleCoupleDTO, midwifeAssessment, currentUser, eligibleUser);

            basicInfo = basicInfoRepository.save(basicInfo);
            midwifeAssessmentRepository.save(midwifeAssessment);

            if (eligibleUser.getRole() == Role.USER) {
                eligibleUser.setRole(Role.ELIGIBLE);
                userRepository.save(eligibleUser);
            }

            log.info("New eligible couple created/updated successfully for user: {} by:{}", eligibleUser.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(basicInfo.getId() + "/Eligible couple created or updated successfully");
        } catch (NotFoundException e) {
            log.error("Not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating/updating eligible couple for user: {}", eligibleCoupleDTO.getUserId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating/updating eligible couple");
        }
    }

    // get eligible data for Midwife
    @Transactional(readOnly = true)
    public ResponseEntity<EligibleCoupleDTO> getEligibleForMidwife(long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(user).orElseGet(BasicInfo::new);
            MidwifeAssessment midwifeAssessment = midwifeAssessmentRepository.findByUserAndDeletedAtIsNull(user).orElseGet(MidwifeAssessment::new);

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
            eligibleCoupleDTO.setRole(user.getRole());

            return ResponseEntity.status(HttpStatus.OK).body(eligibleCoupleDTO);
        } catch (NotFoundException e) {
            log.error("Not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error retrieving eligible couple data for user: {} {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // get all eligible users for midwife
    public ResponseEntity<List<EligibleCoupleResponse>> getEligibleListForMidwife() {
        User currentUser = jwtService.getCurrentUser();

        List<BasicInfo> basicInfoList = basicInfoRepository.findAllByRegion(currentUser.getEmail());
        if (basicInfoList.isEmpty()) {
            throw new NotFoundException("Eligible users not found");
        }

        List<EligibleCoupleResponse> eligibleCoupleResponseList = BasicInfoMapper.toEligibleCoupleResponseList(basicInfoList);
        return ResponseEntity.ok(eligibleCoupleResponseList);
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

    private void mapBasicInfo(EligibleCoupleDTO dto, BasicInfo basicInfo, User byUser, User eligibleUser) {
        if (basicInfo.getId() == 0) {
            basicInfo.setCreatedBy(byUser);
        }
        basicInfo = BasicInfoMapper.toEntityByMidwife(basicInfo, dto);
        basicInfo.setUser(eligibleUser);
        basicInfo.setUpdatedBy(byUser);
        basicInfo.setPastPregnancies(mapPastPregnancies(dto.getPastPregnancies(), basicInfo, byUser));
        basicInfo.setFamilyPlanningMethods(mapFamilyPlanningMethods(dto.getFamilyPlanningMethods(), basicInfo, byUser));

        Employee currentEmployee = employeeRepository.findByUser(byUser).orElseThrow(() -> new NotFoundException("Employee not found"));
        basicInfo.setRegion(currentEmployee.getRegion());
    }

    private List<PastPregnancy> mapPastPregnancies(List<PastPregnancyDTO> dtos, BasicInfo basicInfo, User byUser) {
        return dtos.stream()
                .filter(dto -> dto.getGender() != null && !dto.getGender().isEmpty())
                .map(dto -> {
                    try {
                        PastPregnancy pp = new PastPregnancy();
                        pp.setGender(Gender.valueOf(dto.getGender()));
                        pp.setResult(dto.getResult());
                        pp.setBasicInfo(basicInfo);
                        pp.setCreatedBy(byUser);
                        pp.setUpdatedBy(byUser);
                        return pp;
                    } catch (IllegalArgumentException e) {
                        log.error("Invalid gender value: {} {}", dto.getGender(), e.getMessage());
                        throw new InvalidException("Invalid gender value");
                    } catch (Exception e) {
                        log.error("Error mapping DTO: {}", e.getMessage());
                        throw new InvalidException("Error mapping DTO");
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<FamilyPlanningMethod> mapFamilyPlanningMethods(List<FamilyPlanningMethodDTO> dtos, BasicInfo basicInfo, User byUser) {
        return dtos.stream()
                .filter(dto -> dto.getMethod() != null && !dto.getMethod().isEmpty())
                .map(dto -> {
                    try {
                        FamilyPlanningMethod fpm = new FamilyPlanningMethod();
                        fpm.setDate(dto.getDate());
                        fpm.setMethod(dto.getMethod());
                        fpm.setBasicInfo(basicInfo);
                        fpm.setCreatedBy(byUser);
                        fpm.setUpdatedBy(byUser);
                        return fpm;
                    } catch (IllegalArgumentException e) {
                        log.error("Invalid date value: {} {}", dto.getDate(), e.getMessage());
                        throw new InvalidException("Invalid date value");
                    } catch (Exception e) {
                        log.error("Error mapping DTO: {}", e.getMessage());
                        throw new InvalidException("Error mapping DTO");
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void mapMidwifeAssessment(EligibleCoupleDTO dto, MidwifeAssessment midwifeAssessment, User byUser, User eligibleUser) {
        if (midwifeAssessment.getId() == 0) {
            midwifeAssessment.setCreatedBy(byUser);
        }
        midwifeAssessment = MidwifeAssessmentMapper.toEntityByMidwife(midwifeAssessment, dto);
        midwifeAssessment.setUser(eligibleUser);
        midwifeAssessment.setUpdatedBy(byUser);
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

    public String getRegionNameByUser(User user) {
        return employeeRepository.getRegionName(user);
    }

    public Object[] getMOHDetailsByUser(User user) {
        return employeeRepository.getAreaAndDistrict(user);
    }

    public ResponseEntity<List<EligibleCoupleResponse>> getParentListForMidwife() {
        User currentUser = jwtService.getCurrentUser();

        List<BasicInfo> basicInfoList = basicInfoRepository.findByUserRoleAndRegion(currentUser.getEmail(), Role.PARENT);
        if (basicInfoList.isEmpty()) {
            throw new NotFoundException("Parent users not found");
        }

        List<EligibleCoupleResponse> eligibleCoupleResponseList = BasicInfoMapper.toParentResponseList(basicInfoList);
        return ResponseEntity.ok(eligibleCoupleResponseList);
    }

    public ResponseEntity<String> updateEligibleToParent(long userId, long eligibleId) {
        User user = userRepository.findByUserIdAndBasicInfoIdAndRole(userId, eligibleId, Role.ELIGIBLE)
                .orElseThrow(() -> new NotFoundException("Eligible user not found"));

        user.setRole(Role.PARENT);
        userRepository.save(user);

        log.info("Eligible user {} updated to parent", user.getEmail());
        return ResponseEntity.ok("Eligible user updated to parent");
    }

    // delete eligible user by midwife
    @Transactional
    public ResponseEntity<String> deleteEligibleCouple(long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User eligibleUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

            if (eligibleUser.getRole() == Role.ELIGIBLE) {
                eligibleUserDelete(eligibleUser, currentUser);

                log.info("Eligible user:{} deleted successfully by:{}", eligibleUser.getEmail(), currentUser.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body("Eligible user deleted successfully");
            } else if (eligibleUser.getRole() == Role.PARENT) {
                eligibleUserDelete(eligibleUser, currentUser);

                log.info("Eligible user:{} deleted successfully by:{}", eligibleUser.getEmail(), currentUser.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body("Eligible user deleted successfully");
            } else {
                log.warn("{}:{} is trying to delete unauthorized user:{}:{} as ELIGIBLE user", currentUser.getRole(), currentUser.getEmail(), eligibleUser.getRole(), eligibleUser.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cannot perform delete action on this user");
            }
        } catch (Exception e) {
            log.error("Error deleting eligible user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting eligible use");
        }
    }

    private void eligibleUserDelete(User eligibleUser, User currentUser) {
        // Initially delete midwife created section
        Optional<BasicInfo> basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(eligibleUser);
        Optional<MidwifeAssessment> midwifeAssessment = midwifeAssessmentRepository.findByUserAndDeletedAtIsNull(eligibleUser);

        if (basicInfo.isPresent() && midwifeAssessment.isPresent()) {
            basicInfo.get().setDeletedAt(LocalDateTime.now());
            midwifeAssessment.get().setDeletedAt(LocalDateTime.now());

            basicInfo.get().setDeletedBy(currentUser);
            midwifeAssessment.get().setDeletedBy(currentUser);

            basicInfoRepository.save(basicInfo.get());
            midwifeAssessmentRepository.save(midwifeAssessment.get());

            // reset role to user
            eligibleUser.setRole(Role.USER);
            userRepository.save(eligibleUser);

            // user created section delete
            Optional<MedicalHistory> medicalHistory = medicalHistoryRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<SpecialWoman> specialWoman = specialWomanRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<SpecialBoth> specialBoth = specialBothRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<FamilyHealthInfo> familyHealthInfo = familyHealthInfoRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<FamilyNutrition> familyNutrition = familyNutritionRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<ParentHabit> parentHabit = parentHabitRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            Optional<HomeEnvironment> homeEnvironment = homeEnvironmentRepository.findByUserAndDeletedAtIsNull(eligibleUser);
            if (medicalHistory.isPresent()) {
                medicalHistory.get().setDeletedAt(LocalDateTime.now());
                specialWoman.get().setDeletedAt(LocalDateTime.now());
                specialBoth.get().setDeletedAt(LocalDateTime.now());
                familyHealthInfo.get().setDeletedAt(LocalDateTime.now());
                familyNutrition.get().setDeletedAt(LocalDateTime.now());
                parentHabit.get().setDeletedAt(LocalDateTime.now());
                homeEnvironment.get().setDeletedAt(LocalDateTime.now());

                medicalHistory.get().setDeletedBy(currentUser);
                specialWoman.get().setDeletedBy(currentUser);
                specialBoth.get().setDeletedBy(currentUser);
                familyHealthInfo.get().setDeletedBy(currentUser);
                familyNutrition.get().setDeletedBy(currentUser);
                parentHabit.get().setDeletedBy(currentUser);
                homeEnvironment.get().setDeletedBy(currentUser);

                medicalHistoryRepository.save(medicalHistory.get());
                specialWomanRepository.save(specialWoman.get());
                specialBothRepository.save(specialBoth.get());
                familyHealthInfoRepository.save(familyHealthInfo.get());
                familyNutritionRepository.save(familyNutrition.get());
                parentHabitRepository.save(parentHabit.get());
                homeEnvironmentRepository.save(homeEnvironment.get());
            }
        }
    }
}
