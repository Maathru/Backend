package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    Optional<MedicalHistory> findByUser(User currentUser);
}