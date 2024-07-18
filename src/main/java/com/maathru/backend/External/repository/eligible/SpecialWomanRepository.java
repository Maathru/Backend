package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.SpecialWoman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialWomanRepository extends JpaRepository<SpecialWoman, Long> {
    Optional<SpecialWoman> findByUser(User currentUser);
}
