package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}