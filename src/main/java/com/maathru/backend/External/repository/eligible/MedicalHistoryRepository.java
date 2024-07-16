package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
}