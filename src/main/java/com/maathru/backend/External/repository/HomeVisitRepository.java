package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.HomeVisitsListResponse;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.entity.HomeVisit;
import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomeVisitRepository extends JpaRepository<HomeVisit, Long> {
    Optional<HomeVisit> findByUser(User user);

    @Query("SELECT DISTINCT new com.maathru.backend.Application.dto.response.HomeVisitsListResponse(v.id,v.date,v.time,b.womanName,b.address,v.status) " +
            "FROM HomeVisit hv " +
            "JOIN hv.visits v " +
            "JOIN Employee e on e.user = hv.createdBy " +
            "JOIN BasicInfo b on b.user = hv.user " +
            "WHERE e.user.email = :email " +
            "AND EXTRACT(MONTH FROM v.date) = EXTRACT(MONTH FROM CAST(:date AS DATE)) " +
            "AND EXTRACT(YEAR FROM v.date) = EXTRACT(YEAR FROM CAST(:date AS DATE))")
    List<HomeVisitsListResponse> findHomeVisitsByMonthForMidwife(@Param("date") LocalDate date, @Param("email") String email);

    @Query("SELECT DISTINCT new com.maathru.backend.Application.dto.response.HomeVisitsListResponse(v.id,v.time,b.womanName,b.address,v.status) " +
            "FROM HomeVisit hv " +
            "JOIN hv.visits v " +
            "JOIN Employee e on e.user = hv.createdBy " +
            "JOIN BasicInfo b on b.user = hv.user " +
            "WHERE e.user.email = :email " +
            "AND v.date = :date")
    List<HomeVisitsListResponse> findHomeVisitsByDateForMidwife(@Param("date") LocalDate date, @Param("email") String email);
}
