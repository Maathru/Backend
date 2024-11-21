package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.ObstetricComplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObstetricComplicationRepository extends JpaRepository<ObstetricComplication, Long> {
  Optional<ObstetricComplication> findByUserAndDeletedAtIsNull(User currentUser);
}