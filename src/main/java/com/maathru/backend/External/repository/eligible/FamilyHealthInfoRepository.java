package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.FamilyHealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyHealthInfoRepository extends JpaRepository<FamilyHealthInfo, Long> {
    Optional<FamilyHealthInfo> findByUserAndDeletedAtIsNull(User currentUser);
}