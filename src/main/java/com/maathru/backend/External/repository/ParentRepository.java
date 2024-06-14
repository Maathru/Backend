package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}