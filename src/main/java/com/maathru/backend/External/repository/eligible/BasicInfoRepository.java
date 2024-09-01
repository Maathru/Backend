package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {
    Optional<BasicInfo> findByUser(User currentUser);

    @Query("SELECT COUNT(b) " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region " +
            "WHERE b.user.role = :role")
    long countByUserRoleAndRegion(@Param("email") String email, @Param("role") Role role);

    @Query("SELECT b " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region " +
            "WHERE b.user.role = :role")
    List<BasicInfo> findByUserRoleAndRegion(@Param("email") String email, @Param("role") Role role);

    @Query("SELECT b " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region")
    List<BasicInfo> findAllByRegion(@Param("email") String email);
}
