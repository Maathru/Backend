package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.*;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.ApiException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.maathru.backend.constant.Constant.PENDING_BLOG;
import static com.maathru.backend.enumeration.Gender.FEMALE;
import static com.maathru.backend.enumeration.Gender.MALE;

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
    private final ModelMapper mapper;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @Transactional
    public ResponseEntity<String> createEmployee(EmployeeDto employeeDto) {
        User currentUser = jwtService.getCurrentUser();

        // User registration
        try {
            User user = mapper.map(employeeDto, User.class);

            boolean passwordStatus = user.getPassword() == null;

            authenticationService.encodePassword(user);
            authenticationService.initializeUserFields(user, currentUser);

            user.setRole(Role.valueOf(employeeDto.getDesignation().toUpperCase()));
            user.setRole(authenticationService.determineUserRole(currentUser, user));
            user = userRepository.save(user);
            log.info("User signed up successfully: {} by {}: {}", user.getUsername(), currentUser.getRole(), currentUser.getUserId());

            if (passwordStatus) {
                // after user created successfully send password
                try {
                    emailService.sendNewAccountEmail(user.getFirstName(), user.getEmail(), user.getPassword());
                } catch (Exception e) {
                    log.error("Failed to send account creation email: {}", e.getMessage());
                    throw new ApiException("Failed to send account creation email");
                }
            }

            // Employee registration
            Employee employee = mapper.map(employeeDto, Employee.class);
            employee.setGender(findGender(employee.getNic()));
            employee.setDob(findBirthday(employee.getNic()));
            employee.setUser(user);

            // region and moh set by admin
            Employee admin = employeeRepository.findByUser(currentUser).orElseThrow(() -> new NotFoundException("Employee not found"));
            employee.setRegion(admin.getRegion());
            employee.setMoh(admin.getMoh());

            employee = employeeRepository.save(employee);
            log.info("Employee signed up successfully: {} by {}: {}", employee.getUser().getUsername(), currentUser.getRole(), currentUser.getUserId());
            return ResponseEntity.status(201).body("Employee signed up successfully");
        } catch (NotFoundException e) {
            log.error("{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error during employee signup: {}", e.getMessage());
            throw new ApiException("An error occurred during employee signup");
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
    public ResponseEntity<AdminDashboard> getAdminDashboardData() {
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

    static class MonthData {
        int month;
        int days;

        public MonthData(int month, int days) {
            this.month = month;
            this.days = days;
        }
    }

    private static final List<MonthData> dArray = List.of(
            new MonthData(1, 31),
            new MonthData(2, 29),
            new MonthData(3, 31),
            new MonthData(4, 30),
            new MonthData(5, 31),
            new MonthData(6, 30),
            new MonthData(7, 31),
            new MonthData(8, 31),
            new MonthData(9, 30),
            new MonthData(10, 31),
            new MonthData(11, 30),
            new MonthData(12, 31)
    );

    private static Gender findGender(String nicNumber) {
        int days;

        if (nicNumber.length() == 10) {
            days = Integer.parseInt(nicNumber.substring(2, 5));
        } else {
            days = Integer.parseInt(nicNumber.substring(4, 7));
        }

        return (days < 500) ? MALE : FEMALE;
    }

    private static LocalDate findBirthday(String nicNumber) {
        int year;
        int month = 0;
        int day;

        try {
            if (nicNumber.length() == 10) {
                year = Integer.parseInt(nicNumber.substring(0, 2)) + 1900;
                day = Integer.parseInt(nicNumber.substring(2, 5));
            } else {
                year = Integer.parseInt(nicNumber.substring(0, 4));
                day = Integer.parseInt(nicNumber.substring(4, 7));
            }

            // Adjust day for gender
            if (day >= 500) {
                day -= 500;
            }

            // Determine month and day
            for (MonthData data : dArray) {
                if (day > data.days) {
                    day -= data.days;
                } else {
                    month = data.month;
                    break;
                }
            }

            return LocalDate.of(year, month, day);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid NIC number format", e);
        }
    }
}
