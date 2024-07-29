package com.maathru.backend.External.repository.pregnancy;

import com.maathru.backend.Domain.entity.pregnancycard.ExistingMedicalConditions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExistingMedicalConditionsRepository extends JpaRepository<ExistingMedicalConditions, Long> {
}