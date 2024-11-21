package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.parent.*;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import com.maathru.backend.Domain.entity.parent.*;
import com.maathru.backend.Domain.mapper.ChildBirthMapper;
import com.maathru.backend.Domain.mapper.CurrentPregnancyMapper;
import com.maathru.backend.Domain.mapper.FamilyHistoryMapper;
import com.maathru.backend.Domain.mapper.ParentDetailsMapper;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.External.repository.eligible.MedicalHistoryRepository;
import com.maathru.backend.External.repository.parent.PreExistingMedicalConditionRepository;
import com.maathru.backend.External.repository.parent.PregnancyHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentService {
    private final ChildDetailRepository childDetailRepository;
    private final ObstetricComplicationRepository obstetricComplicationRepository;
    private final ChildBirthRepository childBirthRepository;
    private final ClinicalConservationRepository clinicalConservationRepository;
    private final OtherSituationRepository otherSituationRepository;
    private final CurrentPregnancyRepository currentPregnancyRepository;
    private final JwtService jwtService;
    private final BasicInfoRepository basicInfoRepository;
    private final PreExistingMedicalConditionRepository preExistingMedicalConditionRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PregnancyHistoryRepository pregnancyHistoryRepository;
    private final ModelMapper mapper;

    @Transactional
    public ResponseEntity<String> createOrUpdateParentDetails(ParentDetailsDto parentDetailsDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new);
            PreExistingMedicalCondition preExistingMedicalCondition = preExistingMedicalConditionRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(PreExistingMedicalCondition::new);

            if (basicInfo.getId() == 0) {
                basicInfo.setCreatedBy(currentUser);
            }
            if (preExistingMedicalCondition.getId() == 0) {
                preExistingMedicalCondition.setCreatedBy(currentUser);
            }

            ParentDetailsMapper parentDetailsMapper = new ParentDetailsMapper();

            parentDetailsMapper.toBasicInfo(basicInfo, parentDetailsDto);
            parentDetailsMapper.toPreExistingMedicalCondition(preExistingMedicalCondition, parentDetailsDto);

            basicInfo.setUser(currentUser);
            basicInfo.setUpdatedBy(currentUser);

            preExistingMedicalCondition.setUser(currentUser);
            preExistingMedicalCondition.setUpdatedBy(currentUser);

            basicInfoRepository.save(basicInfo);
            preExistingMedicalConditionRepository.save(preExistingMedicalCondition);

            log.info("Parent Details data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Parent Details data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Parent Details data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Parent Details data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParentDetailsDto> getParentDetails() {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new);
            PreExistingMedicalCondition preExistingMedicalCondition = preExistingMedicalConditionRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(PreExistingMedicalCondition::new);

            ParentDetailsMapper parentDetailsMapper = new ParentDetailsMapper();
            ParentDetailsDto parentDetailsDto = parentDetailsMapper.toParentDetailsDto(basicInfo, preExistingMedicalCondition);

            return ResponseEntity.status(HttpStatus.OK).body(parentDetailsDto);
        } catch (Exception e) {
            log.error("Error retrieving Parent Details data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> createOrUpdateFamilyHistory(FamilyHistoryDto familyHistoryDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            MedicalHistory medicalHistory = medicalHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(MedicalHistory::new);

            if (medicalHistory.getId() == 0) {
                medicalHistory.setCreatedBy(currentUser);
            }

            FamilyHistoryMapper familyHistoryMapper = new FamilyHistoryMapper();
            familyHistoryMapper.toMedicalHistory(medicalHistory, familyHistoryDto);

            medicalHistory.setUser(currentUser);
            medicalHistory.setUpdatedBy(currentUser);

            medicalHistoryRepository.save(medicalHistory);

            log.info("Medical History data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Medical History data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Medical History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Medical History data");
        }
    }

    public ResponseEntity<FamilyHistoryDto> getFamilyHistory() {
        try {
            User currentUser = jwtService.getCurrentUser();

            MedicalHistory medicalHistory = medicalHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(MedicalHistory::new);

            FamilyHistoryMapper familyHistoryMapper = new FamilyHistoryMapper();
            FamilyHistoryDto familyHistoryDto = familyHistoryMapper.toFamilyHistoryDto(medicalHistory);

            return ResponseEntity.status(HttpStatus.OK).body(familyHistoryDto);
        } catch (Exception e) {
            log.error("Error retrieving Medical History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public ResponseEntity<String> createOrUpdatePregnancyHistory(PregnancyHistoryDto pregnancyHistoryDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new);
            PregnancyHistory pregnancyHistory = pregnancyHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(PregnancyHistory::new);

            basicInfo.setChildren(pregnancyHistoryDto.getChildren());
            basicInfo.setUpdatedBy(currentUser);
            basicInfoRepository.save(basicInfo);

            if (pregnancyHistory.getId() == 0) {
                pregnancyHistory.setCreatedBy(currentUser);
            }
            mapper.map(pregnancyHistoryDto, pregnancyHistory);
            pregnancyHistory.setUser(currentUser);
            pregnancyHistory.setCreatedBy(currentUser);
            pregnancyHistory.setUpdatedBy(currentUser);

            pregnancyHistoryRepository.save(pregnancyHistory);

            log.info("Pregnancy History data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Pregnancy History data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Pregnancy History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Pregnancy History data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PregnancyHistoryDto> getPregnancyHistory() {
        try {
            User currentUser = jwtService.getCurrentUser();

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(BasicInfo::new);
            PregnancyHistory pregnancyHistory = pregnancyHistoryRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(PregnancyHistory::new);

            PregnancyHistoryDto pregnancyHistoryDto = mapper.map(pregnancyHistory, PregnancyHistoryDto.class);
            pregnancyHistoryDto.setChildren(basicInfo.getChildren());

            return ResponseEntity.status(HttpStatus.OK).body(pregnancyHistoryDto);
        } catch (Exception e) {
            log.error("Error retrieving Pregnancy History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public ResponseEntity<String> createOrUpdateCurrentPregnancy(CurrentPregnancyDto currentPregnancyDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            CurrentPregnancy currentPregnancy = currentPregnancyRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(CurrentPregnancy::new);
            OtherSituation otherSituation = otherSituationRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(OtherSituation::new);

            if (currentPregnancy.getId() == 0) {
                currentPregnancy.setCreatedBy(currentUser);
            }
            if (otherSituation.getId() == 0) {
                otherSituation.setCreatedBy(currentUser);
            }

            CurrentPregnancyMapper currentPregnancyMapper = new CurrentPregnancyMapper();

            currentPregnancyMapper.toCurrentPregnancy(currentPregnancy, currentPregnancyDto);
            currentPregnancyMapper.toOtherSituation(otherSituation, currentPregnancyDto);

            currentPregnancy.setUser(currentUser);
            currentPregnancy.setUpdatedBy(currentUser);

            otherSituation.setUser(currentUser);
            otherSituation.setUpdatedBy(currentUser);

            currentPregnancyRepository.save(currentPregnancy);
            otherSituationRepository.save(otherSituation);

            log.info("Current Pregnancy data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Current Pregnancy data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Current Pregnancy data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Current Pregnancy data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CurrentPregnancyDto> getCurrentPregnancy() {
        try {
            User currentUser = jwtService.getCurrentUser();

            CurrentPregnancy currentPregnancy = currentPregnancyRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(CurrentPregnancy::new);
            OtherSituation otherSituation = otherSituationRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(OtherSituation::new);

            CurrentPregnancyMapper currentPregnancyMapper = new CurrentPregnancyMapper();
            CurrentPregnancyDto currentPregnancyDto = currentPregnancyMapper.toCurrentPregnancyDto(currentPregnancy, otherSituation);

            return ResponseEntity.status(HttpStatus.OK).body(currentPregnancyDto);
        } catch (Exception e) {
            log.error("Error retrieving Current Pregnancy data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> createClinicalConservation(ClinicalConservationDto clinicalConservationDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            ClinicalConservation clinicalConservation = mapper.map(clinicalConservationDto, ClinicalConservation.class);
            clinicalConservation.setUser(currentUser);
            clinicalConservation.setCreatedBy(currentUser);
            clinicalConservation.setUpdatedBy(currentUser);

            clinicalConservationRepository.save(clinicalConservation);

            log.info("Clinical Conservation added successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Clinical Conservation added successfully");
        } catch (Exception e) {
            log.error("Error saving Clinical Conservation for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Clinical Conservation");
        }
    }

    public ResponseEntity<List<ClinicalConservationDto>> getClinicalConservations() {
        try {
            User currentUser = jwtService.getCurrentUser();

            List<ClinicalConservationDto> clinicalConservationDtos = clinicalConservationRepository.findByUser(currentUser).stream().map((clinic) -> mapper.map(clinic, ClinicalConservationDto.class)).toList();
            return ResponseEntity.status(HttpStatus.OK).body(clinicalConservationDtos);
        } catch (Exception e) {
            log.error("Error retrieving Clinical Conservation for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public ResponseEntity<String> createOrUpdateChildBirth(ChildBirthDto childBirthDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            ChildBirth childBirth = childBirthRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ChildBirth::new);
            ObstetricComplication obstetricComplication = obstetricComplicationRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ObstetricComplication::new);
            ChildDetail childDetail = childDetailRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ChildDetail::new);

            if (childBirth.getId() == 0) {
                childBirth.setCreatedBy(currentUser);
            }
            if (obstetricComplication.getId() == 0) {
                obstetricComplication.setCreatedBy(currentUser);
            }
            if (childDetail.getId() == 0) {
                childDetail.setCreatedBy(currentUser);
            }

            ChildBirthMapper childBirthMapper = new ChildBirthMapper();

            childBirthMapper.toChildBirth(childBirth, childBirthDto);
            childBirthMapper.toObstetricComplication(obstetricComplication, childBirthDto);
            childBirthMapper.toChildDetail(childDetail, childBirthDto);

            childBirth.setUser(currentUser);
            childBirth.setUpdatedBy(currentUser);

            obstetricComplication.setUser(currentUser);
            obstetricComplication.setUpdatedBy(currentUser);

            childDetail.setUser(currentUser);
            childDetail.setUpdatedBy(currentUser);

            childBirthRepository.save(childBirth);
            obstetricComplicationRepository.save(obstetricComplication);
            childDetailRepository.save(childDetail);

            log.info("Child Birth data added or updated successfully by {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Child Birth data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Child Birth data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Child Birth data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ChildBirthDto> getChildBirth() {
        try {
            User currentUser = jwtService.getCurrentUser();

            ChildBirth childBirth = childBirthRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ChildBirth::new);
            ObstetricComplication obstetricComplication = obstetricComplicationRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ObstetricComplication::new);
            ChildDetail childDetail = childDetailRepository.findByUserAndDeletedAtIsNull(currentUser).orElseGet(ChildDetail::new);

            ChildBirthMapper childBirthMapper = new ChildBirthMapper();
            ChildBirthDto childBirthDto = childBirthMapper.toChildBirthDto(childBirth, obstetricComplication, childDetail);

            return ResponseEntity.status(HttpStatus.OK).body(childBirthDto);
        } catch (Exception e) {
            log.error("Error retrieving Child Birth data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
