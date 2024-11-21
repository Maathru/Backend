package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildDetailRepository extends JpaRepository<ChildDetail, Long> {
  Optional<ChildDetail> findByUserAndDeletedAtIsNull(User currentUser);
}