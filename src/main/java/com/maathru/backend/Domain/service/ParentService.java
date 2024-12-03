package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.parent.*;
import com.maathru.backend.Application.dto.request.ChildMemoryDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import com.maathru.backend.Domain.entity.parent.*;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.mapper.ChildBirthMapper;
import com.maathru.backend.Domain.mapper.CurrentPregnancyMapper;
import com.maathru.backend.Domain.mapper.FamilyHistoryMapper;
import com.maathru.backend.Domain.mapper.ParentDetailsMapper;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.External.repository.eligible.MedicalHistoryRepository;
import com.maathru.backend.External.repository.parent.ChildDetailRepository;
import com.maathru.backend.External.repository.parent.ChildMemoryRepository;
import com.maathru.backend.External.repository.parent.PreExistingMedicalConditionRepository;
import com.maathru.backend.External.repository.parent.PregnancyHistoryRepository;
import com.maathru.backend.enumeration.Role;
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
    private final UserRepository userRepository;
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
    private final ChildMemoryRepository childMemoryRepository;

    @Transactional
    public ResponseEntity<String> createOrUpdateParentDetails(Long userId, ParentDetailsDto parentDetailsDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(user).orElseGet(BasicInfo::new);
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

            basicInfo.setUser(user);
            basicInfo.setUpdatedBy(currentUser);

            preExistingMedicalCondition.setUser(user);
            preExistingMedicalCondition.setUpdatedBy(currentUser);

            basicInfoRepository.save(basicInfo);
            preExistingMedicalConditionRepository.save(preExistingMedicalCondition);

            log.info("Parent Details data for {} added or updated successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Parent Details data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Parent Details data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Parent Details data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParentDetailsDto> getParentDetails(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

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

    public ResponseEntity<String> createOrUpdateFamilyHistory(Long userId, FamilyHistoryDto familyHistoryDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            MedicalHistory medicalHistory = medicalHistoryRepository.findByUserAndDeletedAtIsNull(user).orElseGet(MedicalHistory::new);

            if (medicalHistory.getId() == 0) {
                medicalHistory.setCreatedBy(currentUser);
            }

            FamilyHistoryMapper familyHistoryMapper = new FamilyHistoryMapper();
            familyHistoryMapper.toMedicalHistory(medicalHistory, familyHistoryDto);

            medicalHistory.setUser(user);
            medicalHistory.setUpdatedBy(currentUser);

            medicalHistoryRepository.save(medicalHistory);

            log.info("Medical History data for {} added or updated successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Medical History data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Medical History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Medical History data");
        }
    }

    public ResponseEntity<FamilyHistoryDto> getFamilyHistory(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

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
    public ResponseEntity<String> createOrUpdatePregnancyHistory(Long userId, PregnancyHistoryDto pregnancyHistoryDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            BasicInfo basicInfo = basicInfoRepository.findByUserAndDeletedAtIsNull(user).orElseGet(BasicInfo::new);
            PregnancyHistory pregnancyHistory = pregnancyHistoryRepository.findByUserAndDeletedAtIsNull(user).orElseGet(PregnancyHistory::new);

            basicInfo.setChildren(pregnancyHistoryDto.getChildren());
            basicInfo.setUpdatedBy(currentUser);
            basicInfoRepository.save(basicInfo);

            if (pregnancyHistory.getId() == 0) {
                pregnancyHistory.setCreatedBy(currentUser);
            }
            mapper.map(pregnancyHistoryDto, pregnancyHistory);
            pregnancyHistory.setUser(user);
            pregnancyHistory.setCreatedBy(currentUser);
            pregnancyHistory.setUpdatedBy(currentUser);

            pregnancyHistoryRepository.save(pregnancyHistory);

            log.info("Pregnancy History data for {} added or updated successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Pregnancy History data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Pregnancy History data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Pregnancy History data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PregnancyHistoryDto> getPregnancyHistory(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

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
    public ResponseEntity<String> createOrUpdateCurrentPregnancy(Long userId, CurrentPregnancyDto currentPregnancyDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            CurrentPregnancy currentPregnancy = currentPregnancyRepository.findByUserAndDeletedAtIsNull(user).orElseGet(CurrentPregnancy::new);
            OtherSituation otherSituation = otherSituationRepository.findByUserAndDeletedAtIsNull(user).orElseGet(OtherSituation::new);

            if (currentPregnancy.getId() == 0) {
                currentPregnancy.setCreatedBy(currentUser);
            }
            if (otherSituation.getId() == 0) {
                otherSituation.setCreatedBy(currentUser);
            }

            CurrentPregnancyMapper currentPregnancyMapper = new CurrentPregnancyMapper();

            currentPregnancyMapper.toCurrentPregnancy(currentPregnancy, currentPregnancyDto);
            currentPregnancyMapper.toOtherSituation(otherSituation, currentPregnancyDto);

            currentPregnancy.setUser(user);
            currentPregnancy.setUpdatedBy(currentUser);

            otherSituation.setUser(user);
            otherSituation.setUpdatedBy(currentUser);

            currentPregnancyRepository.save(currentPregnancy);
            otherSituationRepository.save(otherSituation);

            log.info("Current Pregnancy data for {} added or updated successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Current Pregnancy data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Current Pregnancy data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Current Pregnancy data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CurrentPregnancyDto> getCurrentPregnancy(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

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

    public ResponseEntity<String> createClinicalConservation(Long userId, ClinicalConservationDto clinicalConservationDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            ClinicalConservation clinicalConservation = mapper.map(clinicalConservationDto, ClinicalConservation.class);
            clinicalConservation.setUser(user);
            clinicalConservation.setCreatedBy(currentUser);
            clinicalConservation.setUpdatedBy(currentUser);

            clinicalConservationRepository.save(clinicalConservation);

            log.info("Clinical Conservation added for {} successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Clinical Conservation added successfully");
        } catch (Exception e) {
            log.error("Error saving Clinical Conservation for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Clinical Conservation");
        }
    }

    public ResponseEntity<List<ClinicalConservationDto>> getClinicalConservations(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

            List<ClinicalConservationDto> clinicalConservationDtos = clinicalConservationRepository.findByUser(currentUser).stream().map((clinic) -> mapper.map(clinic, ClinicalConservationDto.class)).toList();
            return ResponseEntity.status(HttpStatus.OK).body(clinicalConservationDtos);
        } catch (Exception e) {
            log.error("Error retrieving Clinical Conservation for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public ResponseEntity<String> createOrUpdateChildBirth(Long userId, ChildBirthDto childBirthDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user;

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            } else {
                user = currentUser;
            }

            ChildBirth childBirth = childBirthRepository.findByUserAndDeletedAtIsNull(user).orElseGet(ChildBirth::new);
            ObstetricComplication obstetricComplication = obstetricComplicationRepository.findByUserAndDeletedAtIsNull(user).orElseGet(ObstetricComplication::new);
            ChildDetail childDetail = childDetailRepository.findByUserAndDeletedAtIsNull(user).orElseGet(ChildDetail::new);

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

            childBirth.setUser(user);
            childBirth.setUpdatedBy(currentUser);

            obstetricComplication.setUser(user);
            obstetricComplication.setUpdatedBy(currentUser);

            childDetail.setUser(user);
            childDetail.setUpdatedBy(currentUser);

            childBirthRepository.save(childBirth);
            obstetricComplicationRepository.save(obstetricComplication);
            childDetailRepository.save(childDetail);

            log.info("Child Birth data for {} added or updated successfully by {}", user.getEmail(), currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Child Birth data added or updated successfully");
        } catch (Exception e) {
            log.error("Error saving Child Birth data for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Child Birth data");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ChildBirthDto> getChildBirth(Long userId) {
        try {
            User currentUser = jwtService.getCurrentUser();

            if (currentUser.getRole() == Role.MIDWIFE) {
                if (userId == null || userId == 0) {
                    throw new Exception("User id cannot be empty");
                }
                currentUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found "));
            }

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

    public ResponseEntity<String> addMemoryChild(ChildMemoryDto childMemoryDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            ChildDetail childDetail = childDetailRepository.findById(childMemoryDto.getChildId()).orElseThrow(() -> new NotFoundException("Child not found"));

            ChildMemory childMemory = new ChildMemory();
            childMemory.setChild(childDetail);
            childMemory.setCreatedBy(currentUser);
            childMemory.setUpdatedBy(currentUser);
            childMemory.setTitle(childMemoryDto.getTitle());
            childMemory.setDescription(childMemoryDto.getDescription());

            // Save to repository
            childMemoryRepository.save(childMemory);

            log.info("Child memory added successfully by user: {}", currentUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Child memory added successfully");
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error saving child memory for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving child memory");
        }
    }
}
