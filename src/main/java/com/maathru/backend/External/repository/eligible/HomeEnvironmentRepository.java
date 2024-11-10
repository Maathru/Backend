package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.HomeEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeEnvironmentRepository extends JpaRepository<HomeEnvironment, Long> {
    Optional<HomeEnvironment> findByUserAndDeletedAtIsNull(User currentUser);
}
