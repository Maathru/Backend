package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.HomeVisit;
import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeVisitRepository extends JpaRepository<HomeVisit, Long> {
    Optional<HomeVisit> findByUser(User user);
}
