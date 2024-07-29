package com.maathru.backend.External.repository.pregnancy;

import com.maathru.backend.Domain.entity.pregnancycard.FamilyMedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMedicalHistoryRepository extends JpaRepository<FamilyMedicalHistory, Long> {
}