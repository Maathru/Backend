package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.VaccineCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineCardRepository extends JpaRepository<VaccineCard, Long> {
}