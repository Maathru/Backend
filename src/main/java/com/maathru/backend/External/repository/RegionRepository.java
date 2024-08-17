package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionNumber(String regionNumber);

    @Query("SELECT r AS regionName FROM Region r WHERE r.moh.province = :province AND r.moh.district = :district AND r.moh.area = :area")
    List<Region> findRegionsByMOHAttributes(@Param("province") Province province, @Param("district") District district, @Param("area") Area area);

    @Query("SELECT new com.maathru.backend.Application.dto.response.RegionResponse(r.regionId,r.regionName) " +
            "FROM Region r " +
            "JOIN r.moh m " +
            "JOIN Employee e ON e.moh = m " +
            "JOIN e.user u " +
            "WHERE u.email = :email")
    List<RegionResponse> findRegionsByUser(@Param("email") String email);

    @Query("SELECT new com.maathru.backend.Application.dto.response.RegionResponse(r.regionId, r.regionName, r.population, " +
            "COALESCE(e2.user.firstName || ' ' || e2.user.lastName, NULL)) " +
            "FROM Region r " +
            "LEFT JOIN Employee e2 ON e2.region = r AND e2.user.role = :midwife " +
            "JOIN r.moh m " +
            "JOIN Employee e ON e.moh = m " +
            "JOIN e.user u " +
            "WHERE u.email = :email")
    List<RegionResponse> findRegionsByAdmin(@Param("email") String email, @Param("midwife") Role midwife);
}