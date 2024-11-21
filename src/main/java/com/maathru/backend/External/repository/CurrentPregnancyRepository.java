package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.CurrentPregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentPregnancyRepository extends JpaRepository<CurrentPregnancy, Long> {
  Optional<CurrentPregnancy> findByUserAndDeletedAtIsNull(User currentUser);
}