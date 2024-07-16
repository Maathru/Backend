package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.eligible.DoctorAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAssessmentRepository extends JpaRepository<DoctorAssessment, Long> {
}