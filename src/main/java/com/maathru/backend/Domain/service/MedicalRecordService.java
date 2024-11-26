package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.MedicalRecordDto;
import com.maathru.backend.Domain.entity.MedicalRecord;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.MedicalRecordRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ResponseEntity<MedicalRecord> createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new NotFoundException("Author not found");
        }

        MedicalRecord medicalRecord = new MedicalRecord();
        User patient = new User();
        patient = userRepository.findById(medicalRecordDto.getPatientId()).get();
        medicalRecord.setPatient(patient);
        medicalRecord.setDisease(medicalRecordDto.getDisease());
        medicalRecord.setMedicationGiven(medicalRecordDto.getMedicationGiven());
        medicalRecord.setCreatedBy(currentUser);
        medicalRecord.setUpdatedBy(currentUser);
        medicalRecord.setRemarks(medicalRecordDto.getRemarks());
        medicalRecord.setReferredTo(medicalRecordDto.getReferredTo());

        medicalRecord = medicalRecordRepository.save(medicalRecord);
        log.info("Medical Record:{} added successfully by {}", medicalRecord.getRecordId(), currentUser.getEmail());

        return ResponseEntity.status(201).body(medicalRecord);

    }

    public ResponseEntity<Iterable<MedicalRecord>> getAllMedicalRecords() {
        return ResponseEntity.ok(medicalRecordRepository.findAll());
    }

    public ResponseEntity<MedicalRecord> getMedicalRecord(long id) {
        return ResponseEntity.ok(medicalRecordRepository.findById(id).get());
    }

    public ResponseEntity<Iterable<MedicalRecord>> getMedicalRecordByPatient(long patientId) {
        return ResponseEntity.ok(medicalRecordRepository.findByPatientUserId(patientId));
    }
}
