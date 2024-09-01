package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.*;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.maathru.backend.constant.Constant.PENDING_BLOG;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private final BasicInfoRepository basicInfoRepository;
    private final RegionRepository regionRepository;
    private final ClinicRepository clinicRepository;
    private final BlogRepository blogRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ResponseEntity<Employee> createEmployee(EmployeeDto employeeDto) {
        Optional<User> optionalUser = userRepository.findById(employeeDto.getUser());

        if (optionalUser.isPresent()) {
            Employee employee = new Employee();
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setNic(employeeDto.getNic());
            employee.setAddressLine1(employeeDto.getAddressLine1());
            employee.setStreet(employeeDto.getStreet());
            employee.setCity(employeeDto.getCity());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setQualifications(employeeDto.getQualifications());
            employee.setUser(optionalUser.get());
            employee = employeeRepository.save(employee);

            return ResponseEntity.status(201).body(employee);
        } else {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

    }

    public ResponseEntity<MidwifeResponse> getMidwifeByRegionId(long regionId) {
        Employee employee = employeeRepository.findByUserRoleAndRegionId(Role.MIDWIFE, regionId).orElseThrow(() -> new NotFoundException("Midwife not found in this region"));

        MidwifeResponse midwifeResponse = new MidwifeResponse();
        midwifeResponse.setId(employee.getUser().getUserId());
        midwifeResponse.setName(employee.getUser().getFirstName() + " " + employee.getUser().getLastName());
        midwifeResponse.setEmail(employee.getUser().getEmail());
        midwifeResponse.setPhone(employee.getPhoneNumber());
        midwifeResponse.setAddress(employee.getAddressLine1() + ", " + employee.getStreet() + ", " + employee.getCity());

        return ResponseEntity.status(200).body(midwifeResponse);
    }

    public ResponseEntity<List<MidwifeListResponse>> getMidwifesInCurrentMoh() {
        User user = jwtService.getCurrentUser();

        List<MidwifeListResponse> midwifeListResponses = employeeRepository.findMidwifesByUserAndRole(user, Role.MIDWIFE);

        if (midwifeListResponses.isEmpty()) {
            log.error("MIDWIFE list is empty in this region");
            throw new NotFoundException("MIDWIFE list is empty in this region");
        }

        return ResponseEntity.status(200).body(midwifeListResponses);
    }

    public ResponseEntity<List<DoctorsResponse>> getMidwifesInCurrentMohForRegions() {
        User user = jwtService.getCurrentUser();

        List<DoctorsResponse> midwifeListResponses = employeeRepository.findMidwifesByUserAndRoleForRegions(user, Role.MIDWIFE);

        if (midwifeListResponses.isEmpty()) {
            log.error("MIDWIFE list is empty in this region");
            throw new NotFoundException("MIDWIFE list is empty in this region");
        }

        return ResponseEntity.status(200).body(midwifeListResponses);
    }

    public ResponseEntity<List<DoctorsResponse>> getDoctorsForClinics() {
        try {
            User user = jwtService.getCurrentUser();
            List<DoctorsResponse> doctors = employeeRepository.findEmployeesByUserAndRole(user, Role.DOCTOR);

            return ResponseEntity.status(201).body(doctors);
        } catch (Exception e) {
            log.error("Error retrieving current user moh doctors {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // For Admin Dashboard
    public ResponseEntity<AdminDashboard> getAdminDashboardData(){
        User user = jwtService.getCurrentUser();

        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setUsers(userRepository.countByEnabled(true));
        adminDashboard.setBlogsToConfirm(blogRepository.countByApprovalStatus(PENDING_BLOG));
        adminDashboard.setThisMonthClinics(clinicRepository.countClinicsInCurrentMonth());
        adminDashboard.setRegions(regionRepository.countByEmployeeAndMOH(user.getEmail()));

        return ResponseEntity.ok(adminDashboard);
    }

    public ResponseEntity<MidwifeDashboard> getMidwifeDashboardData() {
        User user = jwtService.getCurrentUser();

        MidwifeDashboard midwifeDashboard = new MidwifeDashboard();
        midwifeDashboard.setEligibles(basicInfoRepository.countByUserRoleAndRegion(user.getEmail(), Role.ELIGIBLE));
        midwifeDashboard.setParents(basicInfoRepository.countByUserRoleAndRegion(user.getEmail(), Role.PARENT));
        midwifeDashboard.setClinics(clinicRepository.countClinicsInCurrentMonthAndRegion(user.getEmail()));

        return ResponseEntity.ok(midwifeDashboard);
    }
}
