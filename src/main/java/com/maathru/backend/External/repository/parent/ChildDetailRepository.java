package com.maathru.backend.External.repository.parent;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.ChildDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildDetailRepository extends JpaRepository<ChildDetail, Long> {
  Optional<ChildDetail> findByUserAndDeletedAtIsNull(User currentUser);
}