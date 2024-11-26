package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Iterable<MedicalRecord> findByPatientUserId(long patientId);
}