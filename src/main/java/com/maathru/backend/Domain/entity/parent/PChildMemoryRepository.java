package com.maathru.backend.Domain.entity.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PChildMemoryRepository extends JpaRepository<PChildMemory, Integer> {
}
