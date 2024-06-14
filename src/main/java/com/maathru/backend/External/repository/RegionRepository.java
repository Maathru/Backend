package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}