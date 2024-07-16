package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.eligible.FamilyHealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyHealthInfoRepository extends JpaRepository<FamilyHealthInfo, Long> {
}