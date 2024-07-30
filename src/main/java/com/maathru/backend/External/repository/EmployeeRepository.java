package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserEmail(String userEmail);

    @Query("SELECT e FROM Employee e WHERE e.user.role = :role AND e.region.regionId = :regionId")
    Optional<Employee> findByUserRoleAndRegionId(@Param("role") Role role, @Param("regionId") Long regionId);

    @Query("SELECT r.regionName FROM Employee e JOIN e.region r WHERE e.user = :user")
    String getRegionName(@Param("user") User user);

    @Query("SELECT m.area, m.district FROM Employee e JOIN e.region r JOIN r.moh m WHERE e.user = :user")
    Object[] getAreaAndDistrict(@Param("user") User user);

    @Query("SELECT new com.maathru.backend.Application.dto.response.DoctorsResponse(e.employeeId, e.user.firstName || ' ' || e.user.lastName) " +
            "FROM Employee e " +
            "WHERE e.moh.district = :district " +
            "AND e.moh.area = :area " +
            "AND e.user.role = :role")
    List<DoctorsResponse> findEmployeesByDistrictAndRegion(@Param("district") District district, @Param("area") Area area, @Param("role") Role role);

    Optional<Employee> findByEmployeeIdAndUserRole(Long employeeId, Role role);
}