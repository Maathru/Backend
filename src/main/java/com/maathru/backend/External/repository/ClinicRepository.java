package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.ClinicResponse;
import com.maathru.backend.Domain.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicListResponse(c.clinicId,c.name,c.region.regionName) " +
            "FROM User u " +
            "JOIN Employee e on e.user=u " +
            "JOIN Clinic c on c.moh=e.moh " +
            "WHERE c.date = :date " +
            "AND u.email=:email ")
    List<ClinicListResponse> findClinicsByDate(@Param("date") LocalDate date, @Param("email") String email);


    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicListResponse(c.clinicId, c.name, c.date, c.startTime, c.endTime,c.region.regionName) " +
            "FROM User u " +
            "JOIN Employee e on e.user = u " +
            "JOIN Clinic c on c.moh = e.moh " +
            "WHERE u.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:date AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:date AS DATE))")
    List<ClinicListResponse> findClinicsByMonth(@Param("date") LocalDate date, @Param("email") String email);

    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicListResponse(c.clinicId, c.name, c.date, c.startTime, c.endTime) " +
            "FROM BasicInfo b " +
            "JOIN Region r on r.regionId = b.region.regionId " +
            "JOIN Clinic c on c.moh = r.moh " +
            "AND b.user.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:date AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:date AS DATE))")
    List<ClinicListResponse> findClinicsByMonthForParent(@Param("date") LocalDate date, @Param("email") String email);


    @Query("SELECT DISTINCT c.date " +
            "FROM Clinic c " +
            "JOIN Employee e ON c.moh = e.moh " +
            "JOIN User u ON e.user = u " +
            "WHERE u.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:currentDate AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:currentDate AS DATE))")
    List<LocalDate> findAllClinicDatesForCurrentMonthByAdmin(@Param("currentDate") LocalDate currentDate, @Param("email") String email);

    @Query("SELECT DISTINCT c.date " +
            "FROM Clinic c " +
            "JOIN Employee e ON c.region = e.region " +
            "WHERE e.user.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:currentDate AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:currentDate AS DATE))")
    List<LocalDate> findAllClinicDatesForCurrentMonthByMidwife(@Param("currentDate") LocalDate currentDate, @Param("email") String email);

    @Query("SELECT DISTINCT c.date " +
            "FROM Clinic c " +
            "JOIN c.doctors d " +
            "WHERE d.user.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:currentDate AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:currentDate AS DATE))")
    List<LocalDate> findAllClinicDatesForCurrentMonthByDoctor(@Param("currentDate") LocalDate currentDate, @Param("email") String email);

    @Query("SELECT DISTINCT c.date " +
            "FROM BasicInfo b " +
            "JOIN Clinic c ON c.region = b.region " +
            "WHERE b.user.email = :email " +
            "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM CAST(:currentDate AS DATE)) " +
            "AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:currentDate AS DATE))")
    List<LocalDate> findAllClinicDatesForCurrentMonthByParent(@Param("currentDate") LocalDate currentDate, @Param("email") String email);

    @Query("SELECT c " +
            "FROM User u " +
            "JOIN Employee e on e.user = u " +
            "JOIN e.moh m " +
            "JOIN Clinic c on c.moh = m " +
            "LEFT JOIN FETCH c.doctors d " +
            "WHERE c.clinicId = :clinicId " +
            "AND u.email = :email")
    Optional<Clinic> findClinicWithDoctorsById(@Param("clinicId") Long clinicId, @Param("email") String email);

    @Query(value = "SELECT COUNT(c) FROM Clinic c WHERE MONTH(c.date) = MONTH(CURRENT_DATE()) AND YEAR(c.date) = YEAR(CURRENT_DATE())")
    long countClinicsInCurrentMonth();

    @Query(value = "SELECT COUNT(c) " +
            "FROM Clinic c " +
            "JOIN Employee e ON e.user.email = :email " +
            "JOIN c.region r ON r = e.region " +
            "WHERE MONTH(c.date) = MONTH(CURRENT_DATE()) " +
            "AND YEAR(c.date) = YEAR(CURRENT_DATE())")
    long countClinicsInCurrentMonthAndRegion(@Param("email") String email);

    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicResponse(c.name, c.date, c.startTime, c.endTime) " +
            "FROM Clinic c " +
            "JOIN Employee e ON c.region = e.region " +
            "WHERE e.user.email = :email " +
            "AND c.date >= CURRENT_DATE " +
            "ORDER BY c.date ASC " +
            "LIMIT 10")
    List<ClinicResponse> findUpcomingClinicsForMidwife(@Param("email") String email);

    @Query("SELECT new com.maathru.backend.Application.dto.response.ClinicResponse(c.clinicId,c.name, c.date,c.startTime,c.endTime,c.region.regionName) " +
            "FROM Clinic c " +
            "JOIN c.doctors d " +
            "WHERE d.user.email = :email " +
            "AND c.date >= CURRENT_DATE " +
            "ORDER BY c.date ASC " +
            "LIMIT 10")
    List<ClinicResponse> findUpcomingClinicsForDoctor(@Param("email") String email);
}