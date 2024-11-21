package com.maathru.backend.External.repository.parent;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.PregnancyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PregnancyHistoryRepository extends JpaRepository<PregnancyHistory, Long> {
    Optional<PregnancyHistory> findByUserAndDeletedAtIsNull(User currentUser);
}
