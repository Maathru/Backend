package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.ParentHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentHabitRepository extends JpaRepository<ParentHabit, Long> {
    Optional<ParentHabit> findByUserAndDeletedAtIsNull(User currentUser);
}
