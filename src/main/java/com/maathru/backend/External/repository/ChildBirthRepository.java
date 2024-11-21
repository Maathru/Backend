package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.ChildBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildBirthRepository extends JpaRepository<ChildBirth, Long> {
  Optional<ChildBirth> findByUserAndDeletedAtIsNull(User currentUser);
}