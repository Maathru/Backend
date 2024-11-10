package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.MidwifeAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MidwifeAssessmentRepository extends JpaRepository<MidwifeAssessment, Long> {
    Optional<MidwifeAssessment> findByUserAndDeletedAtIsNull(User currentUser);
}