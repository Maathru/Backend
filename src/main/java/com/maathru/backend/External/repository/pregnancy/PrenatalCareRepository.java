package com.maathru.backend.External.repository.pregnancy;

import com.maathru.backend.Domain.entity.pregnancycard.PrenatalCare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenatalCareRepository extends JpaRepository<PrenatalCare, Long> {
}