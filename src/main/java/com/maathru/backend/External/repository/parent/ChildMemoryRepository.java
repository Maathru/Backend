package com.maathru.backend.External.repository.parent;

import com.maathru.backend.Domain.entity.parent.ChildMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildMemoryRepository extends JpaRepository<ChildMemory, Long> {
}
