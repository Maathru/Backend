package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Domain.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicListResponse(c.clinicId,c.name,c.region.regionName) " +
            "FROM User u " +
            "JOIN Employee e on e.user=u " +
            "JOIN Clinic c on c.moh=e.moh " +
            "WHERE c.date = :date " +
            "AND u.email=:email ")
    List<ClinicListResponse> findClinicsByDate(@Param("date") LocalDate date, @Param("email") String email);

    @Query("SELECT DISTINCT c.date " +
            "FROM Clinic c " +
            "JOIN Employee e ON c.moh = e.moh " +
            "JOIN User u ON e.user = u " +
            "WHERE u.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:currentDate AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:currentDate AS DATE))")
    List<LocalDate> findAllClinicDatesForCurrentMonth(@Param("currentDate") LocalDate currentDate, @Param("email") String email);


}