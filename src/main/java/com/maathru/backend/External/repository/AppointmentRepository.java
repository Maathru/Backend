package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}