package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.parent.ChildBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildBirthRepository extends JpaRepository<ChildBirth, Long> {
  Optional<ChildBirth> findByUserAndDeletedAtIsNull(User currentUser);

  // Query to get the count of births per month
  @Query("SELECT MONTH(cb.dob) AS month, COUNT(cb) AS count FROM ChildBirth cb GROUP BY MONTH(cb.dob) ORDER BY month")
  List<Object[]> getBirthsCountByMonth();
}