package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}