package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.MedicalRecordDto;
import com.maathru.backend.Application.dto.response.MedicalRecordResponse;
import com.maathru.backend.Domain.entity.MedicalRecord;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.MedicalRecordRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<Iterable<MedicalRecordResponse>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<MedicalRecordResponse> medicalRecordResponses = new ArrayList<>();

        for (MedicalRecord medicalRecord : medicalRecords) {
            MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();
            medicalRecordResponse.setRecordId(medicalRecord.getRecordId());
            medicalRecordResponse.setPatientId(medicalRecord.getPatient().getUserId());
            medicalRecordResponse.setPatientName(medicalRecord.getPatient().getFirstName() + " " + medicalRecord.getPatient().getLastName());
            medicalRecordResponse.setDisease(medicalRecord.getDisease());
            medicalRecordResponse.setMedicationGiven(medicalRecord.getMedicationGiven());
            medicalRecordResponse.setRemarks(medicalRecord.getRemarks());
            medicalRecordResponse.setReferredTo(medicalRecord.getReferredTo());

            medicalRecordResponses.add(medicalRecordResponse);
        }

        return ResponseEntity.ok(medicalRecordResponses);
    }

    public ResponseEntity<MedicalRecordResponse> getMedicalRecord(long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).get();
        MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();
        medicalRecordResponse.setRecordId(medicalRecord.getRecordId());
        medicalRecordResponse.setPatientId(medicalRecord.getPatient().getUserId());
        medicalRecordResponse.setPatientName(medicalRecord.getPatient().getFirstName() + " " + medicalRecord.getPatient().getLastName());
        medicalRecordResponse.setDisease(medicalRecord.getDisease());
        medicalRecordResponse.setMedicationGiven(medicalRecord.getMedicationGiven());
        medicalRecordResponse.setRemarks(medicalRecord.getRemarks());
        medicalRecordResponse.setReferredTo(medicalRecord.getReferredTo());
        return ResponseEntity.ok(medicalRecordResponse);
    }

    public ResponseEntity<Iterable<MedicalRecordResponse>> getMedicalRecordByPatient(long patientId) {
        User patient = userRepository.findById(patientId).
                orElseThrow(() -> new NotFoundException("Patient not found"));
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatient(patient);
        List<MedicalRecordResponse> medicalRecordResponses = new ArrayList<>();

        for (MedicalRecord medicalRecord : medicalRecords) {
            MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();
            medicalRecordResponse.setRecordId(medicalRecord.getRecordId());
            medicalRecordResponse.setPatientId(medicalRecord.getPatient().getUserId());
            medicalRecordResponse.setPatientName(medicalRecord.getPatient().getFirstName() + " " + medicalRecord.getPatient().getLastName());
            medicalRecordResponse.setDisease(medicalRecord.getDisease());
            medicalRecordResponse.setMedicationGiven(medicalRecord.getMedicationGiven());
            medicalRecordResponse.setRemarks(medicalRecord.getRemarks());
            medicalRecordResponse.setReferredTo(medicalRecord.getReferredTo());

            medicalRecordResponses.add(medicalRecordResponse);
        }

        return ResponseEntity.ok(medicalRecordResponses);

    }
}
