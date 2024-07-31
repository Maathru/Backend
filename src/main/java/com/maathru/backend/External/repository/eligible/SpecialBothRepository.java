package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.SpecialBoth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialBothRepository extends JpaRepository<SpecialBoth, Long> {
    Optional<SpecialBoth> findByUser(User currentUser);
}
