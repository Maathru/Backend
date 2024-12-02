package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.MedicalRecord;
import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Iterable<MedicalRecord> findByPatientUserId(long patientId);

    List<MedicalRecord> findByPatient(User patient);
}