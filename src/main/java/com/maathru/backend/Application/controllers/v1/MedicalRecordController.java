package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.MedicalRecordDto;
import com.maathru.backend.Domain.entity.MedicalRecord;
import com.maathru.backend.Domain.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/record")
@AllArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<MedicalRecord> createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.createMedicalRecord(medicalRecordDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<Iterable<MedicalRecord>> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(long id) {
        return medicalRecordService.getMedicalRecord(id);
    }

    @GetMapping("/getbypatient/{patientId}")
    public ResponseEntity<Iterable<MedicalRecord>> getMedicalRecordByPatient(long patientId) {
        return medicalRecordService.getMedicalRecordByPatient(patientId);
    }


}
