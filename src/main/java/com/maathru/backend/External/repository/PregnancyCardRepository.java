package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PregnancyCardRepository extends JpaRepository<PregnancyCard, Long> {
    List<PregnancyCard> findByParent(Parent parent);

    @Query("SELECT r.regionName AS regionName, COUNT(p) AS pregnancyCount " +
            "FROM PregnancyCard p JOIN p.region r " +
            "GROUP BY r.regionName")
    List<Map<String, Object>> getPregnancyCountByRegion();
}