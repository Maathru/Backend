package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.OtherSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtherSituationRepository extends JpaRepository<OtherSituation, Long> {
  Optional<OtherSituation> findByUserAndDeletedAtIsNull(User currentUser);
  long countByDiabetesTrue();
  long countByMalariaTrue();
  long countByHeartDiseasesTrue();
  long countByKidneyDiseasesTrue();
}