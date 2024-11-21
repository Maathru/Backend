package com.maathru.backend.External.repository.parent;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.PreExistingMedicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreExistingMedicalConditionRepository extends JpaRepository<PreExistingMedicalCondition, Long> {
    Optional<PreExistingMedicalCondition> findByUserAndDeletedAtIsNull(User currentUser);
}
