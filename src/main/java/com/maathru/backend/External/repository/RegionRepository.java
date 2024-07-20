package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
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
}