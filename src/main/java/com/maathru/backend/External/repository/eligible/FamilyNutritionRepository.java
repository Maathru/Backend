package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.FamilyNutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyNutritionRepository extends JpaRepository<FamilyNutrition, Long> {
    Optional<FamilyNutrition> findByUserAndDeletedAtIsNull(User currentUser);
}