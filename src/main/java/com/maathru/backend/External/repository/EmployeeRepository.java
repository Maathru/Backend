package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.MidwifeListResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.MOH;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Query(value = "INSERT INTO employees (employee_id,phone_number, nic, gender, dob, address_line1, street, city, designation, qualifications, user_user_id, moh_moh_id, region_id) " +
            "VALUES (:employeeId,:phoneNumber, :nic, :gender, :dob, :addressLine1, :street, :city, :designation, :qualifications, :userId, :mohId, :regionId)",
            nativeQuery = true)
    int saveEmployee(@Param("employeeId") Long employeeId,
                     @Param("phoneNumber") String phoneNumber,
                     @Param("nic") String nic,
                     @Param("gender") Gender gender,
                     @Param("dob") LocalDate dob,
                     @Param("addressLine1") String addressLine1,
                     @Param("street") String street,
                     @Param("city") String city,
                     @Param("designation") String designation,
                     @Param("qualifications") String qualifications,
                     @Param("userId") Long userId,
                     @Param("mohId") Long mohId,
                     @Param("regionId") Long regionId);


    Optional<Employee> findByUserEmail(String userEmail);

    @Query("SELECT e FROM Employee e WHERE e.user.role = :role AND e.region.regionId = :regionId")
    Optional<Employee> findByUserRoleAndRegionId(@Param("role") Role role, @Param("regionId") Long regionId);

    @Query("SELECT r.regionName FROM Employee e JOIN e.region r WHERE e.user = :user")
    String getRegionName(@Param("user") User user);

    @Query("SELECT m.area, m.district FROM Employee e JOIN e.region r JOIN r.moh m WHERE e.user = :user")
    Object[] getAreaAndDistrict(@Param("user") User user);

    Optional<Employee> findByEmployeeIdAndUserRole(Long employeeId, Role role);

    Optional<Employee> findByUser(User user);

    @Query("SELECT new com.maathru.backend.Application.dto.response.DoctorsResponse(e2.employeeId, e2.user.firstName || ' ' || e2.user.lastName) " +
            "FROM Employee e " +
            "JOIN e.moh m " +
            "JOIN Employee e2 ON e2.moh = m " +
            "JOIN e2.user u " +
            "WHERE e.user = :user AND u.role = :role")
    List<DoctorsResponse> findEmployeesByUserAndRole(@Param("user") User user, @Param("role") Role role);

    @Query("SELECT new com.maathru.backend.Application.dto.response.MidwifeListResponse(e2.user.userId, e2.user.firstName || ' ' || e2.user.lastName,e2.user.email,e2.phoneNumber,e2.addressLine1 || ',' || e2.street || ',' || e2.city,e2.region.regionName) " +
            "FROM Employee e " +
            "JOIN e.moh m " +
            "JOIN Employee e2 ON e2.moh = m " +
            "JOIN e2.user u " +
            "WHERE e.user = :user AND u.role = :role")
    List<MidwifeListResponse> findMidwifesByUserAndRole(@Param("user") User user, @Param("role") Role role);

    @Query("SELECT new com.maathru.backend.Application.dto.response.DoctorsResponse(e2.employeeId, e2.user.firstName || ' ' || e2.user.lastName) " +
            "FROM Employee e " +
            "JOIN e.moh m " +
            "JOIN Employee e2 ON e2.moh = m " +
            "JOIN e2.user u " +
            "WHERE e.user = :user AND u.role = :role")
    List<DoctorsResponse> findMidwifesByUserAndRoleForRegions(@Param("user") User user, @Param("role") Role role);

    @Modifying
    @Query(value = "INSERT INTO employees (employee_id,phone_number, nic, gender, dob, address_line1, street, city, designation, qualifications, user_user_id, moh_moh_id, region_id) " +
            "VALUES (:employeeId,:phoneNumber, :nic, :gender, :dob, :addressLine1, :street, :city, :designation, :qualifications, :userId, :mohId, :regionId)",
            nativeQuery = true)
    int saveEmployee(@Param("employeeId") Long employeeId,
                     @Param("phoneNumber") String phoneNumber,
                     @Param("nic") String nic,
                     @Param("gender") Gender gender,
                     @Param("dob") LocalDate dob,
                     @Param("addressLine1") String addressLine1,
                     @Param("street") String street,
                     @Param("city") String city,
                     @Param("designation") String designation,
                     @Param("qualifications") String qualifications,
                     @Param("userId") Long userId,
                     @Param("mohId") Long mohId,
                     @Param("regionId") Long regionId);

}