package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.VisitSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitScheduleRepository extends JpaRepository<VisitSchedule, Long> {
}