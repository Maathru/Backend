package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Application.dto.response.HomeVisitsResponse;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {
    Optional<BasicInfo> findByUserAndDeletedAtIsNull(User currentUser);

    @Query("SELECT COUNT(b) " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region " +
            "WHERE b.user.role = :role " +
            "AND b.deletedAt IS NULL")
    long countByUserRoleAndRegion(@Param("email") String email, @Param("role") Role role);

    @Query("SELECT b " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region " +
            "WHERE b.user.role = :role " +
            "AND b.deletedAt IS NULL")
    List<BasicInfo> findByUserRoleAndRegion(@Param("email") String email, @Param("role") Role role);

    @Query("SELECT b " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r2 ON r2 = e.region " +
            "WHERE b.deletedAt IS NULL")
    List<BasicInfo> findAllByRegion(@Param("email") String email);

    @Query("SELECT new com.maathru.backend.Application.dto.response.HomeVisitsResponse(b.womanName, b.manName, b.address, b.womanPhone, b.manPhone, b.location) " +
            "FROM BasicInfo b " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN b.region r ON r = e.region " +
            "WHERE b.user.userId = :id " +
            "AND b.user.role = 'PARENT'")
    HomeVisitsResponse findByParent(@Param("email") String email, @Param("id") long id);


    @Query(value = "SELECT b.woman_age AS age, COUNT(b.id) AS eligibleCount " +
            "FROM user_basic_info b " +
            "GROUP BY b.woman_age " +
            "ORDER BY b.woman_age ASC",
            nativeQuery = true)
    List<Map<String, Object>> getPregnancyCountByAge();



}
