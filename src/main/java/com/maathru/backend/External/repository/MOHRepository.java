package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.MOH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MOHRepository extends JpaRepository<MOH, Long> {
    Optional<MOH> findByMohRegistrationNumber(String mohRegistrationNumber);
}