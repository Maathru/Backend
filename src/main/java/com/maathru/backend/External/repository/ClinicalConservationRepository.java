package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.parent.ClinicalConservationDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.ClinicalConservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalConservationRepository extends JpaRepository<ClinicalConservation, Long> {
  List<ClinicalConservation> findByUser(User currentUser);
}