package com.maathru.backend.Application.config;

import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.MOH;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.MOHRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.maathru.backend.enumeration.District.COLOMBO;
import static com.maathru.backend.enumeration.Province.WESTERN;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitialDataLoader {

    private final UserRepository userRepository;
    private final MOHRepository mohRepository;
    private final EmployeeRepository employeeRepository;
    private final RegionRepository regionRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create and save users
            List<User> users = List.of(
                    new User(1L, "admin@maathru.com", "Admin", "Buddhika", Role.ADMIN),
                    new User(2L, "doctor@maathru.com", "Doctor", "Buddhika", Role.DOCTOR),
                    new User(3L, "midwife@maathru.com", "Midwife", "Buddhika", Role.MIDWIFE),
                    new User(4L, "parent@maathru.com", "Parent", "Buddhika", Role.PARENT),
                    new User(5L, "eligible@maathru.com", "Eligible", "Buddhika", Role.ELIGIBLE),
                    new User(6L, "pending@maathru.com", "Pending", "Buddhika", Role.PENDING),
                    new User(7L, "user@maathru.com", "User", "Buddhika", Role.USER)
            );

            for (User user : users) {
                if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                    user.setAccountNonExpired(true);
                    user.setAccountNonLocked(true);
                    user.setCreatedAt(LocalDateTime.now());
                    user.setEnabled(true);
                    user.setLoginAttempts(0);
                    user.setPassword(passwordEncoder.encode("zaq123"));
                    user.setUpdatedAt(LocalDateTime.now());
                    user.setCreatedBy(user);
                    user.setUpdatedBy(user);
                    userRepository.save(user);
                    log.info("{} user created successfully.", user.getFirstName());
                } else {
                    log.warn("{} user already exists.", user.getFirstName());
                }
            }

            // Get the admin user
            User adminUser = userRepository.findByEmail("admin@maathru.com").orElseThrow(() -> new RuntimeException("Admin user not found"));

            // Create and save MOHs
            List<MOH> mohList = List.of(
                    new MOH(1L, "M/W/1", WESTERN, COLOMBO, Area.NUGEGODA, "No 31", "Temple Road", "Udahamulla", "0712345678", "0712345678", 8000L),
                    new MOH(2L, "M/W/2", WESTERN, COLOMBO, Area.MAHARAGAMA, "No 20/4", "High level Road", "Maharagama", "0723456789", "072345679", 6000L),
                    new MOH(3L, "M/W/3", WESTERN, COLOMBO, Area.BORALESGAMUWA, "No 11", "Piliyndala Road", "Bokundara", "0722345678", "0722345678", 1000L),
                    new MOH(4L, "M/W/4", WESTERN, COLOMBO, Area.PILIYANDALA, "No 98/5", "Samagi Mawatha", "Kesbewa", "0742345678", "0742345678", 3000L)
            );

            for (MOH moh : mohList) {
                if (mohRepository.findByMohRegistrationNumber(moh.getMohRegistrationNumber()).isEmpty()) {
                    moh.setCreatedBy(adminUser);
                    moh.setUpdatedBy(adminUser);
                    moh.setCreatedAt(LocalDateTime.now());
                    moh.setUpdatedAt(LocalDateTime.now());
                    mohRepository.save(moh);
                    log.info("MOH {} created successfully", moh.getMohRegistrationNumber());
                } else {
                    log.warn("MOH {} already exists.", moh.getMohRegistrationNumber());
                }
            }

            // get Moh
            MOH moh = mohRepository.findByMohRegistrationNumber("M/W/1").orElseThrow(() -> new RuntimeException("MOH not found"));

            // Create and save Regions
            List<Region> regions = List.of(
                    new Region(1L, "R/W/1", "Udahamulla", 2000L, moh),
                    new Region(2L, "R/W/2", "Nugegoda", 2000L, moh),
                    new Region(3L, "R/W/3", "Navinna", 1000L, moh),
                    new Region(4L, "R/W/4", "Ambuldeniya", 2000L, moh),
                    new Region(5L, "R/W/5", "Delkanda", 1000L, moh)
            );

            for (Region region : regions) {
                if (regionRepository.findByRegionNumber(region.getRegionNumber()).isEmpty()) {
                    region.setCreatedBy(adminUser);
                    region.setUpdatedBy(adminUser);
                    region.setCreatedAt(LocalDateTime.now());
                    region.setUpdatedAt(LocalDateTime.now());
                    regionRepository.save(region);
                    log.info("Region {} created successfully", region.getRegionNumber());
                } else {
                    log.warn("Region {} already exists.", region.getRegionNumber());
                }
            }

            // get region
            Region region = regionRepository.findByRegionNumber("R/W/1").orElseThrow(() -> new RuntimeException("Region not found"));

            // get doctor and midwife
            User doctor = userRepository.findByEmail("doctor@maathru.com").orElseThrow(() -> new RuntimeException("Doctor not found"));
            User midwife = userRepository.findByEmail("midwife@maathru.com").orElseThrow(() -> new RuntimeException("Midwife not found"));

            // Create and save Employees
            List<Employee> employees = List.of(
                    new Employee(1L, "071234567", "200121503122", Gender.MALE, LocalDate.parse("2001-08-02", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/16", "Wijerama Road", "Udahamulla", "System Admin", "Bsc in CS", adminUser,moh),
                    new Employee(2L, "072234567", "200121503123", Gender.MALE, LocalDate.parse("2001-08-03", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/17", "Wijerama Road", "Udahamulla", "MOH Doctor", "MBbs", doctor,moh),
                    new Employee(3L, "074234567", "200121503124", Gender.FEMALE, LocalDate.parse("2001-08-04", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/18", "Wijerama Road", "Udahamulla", "Midwife", "Dip in Zoology", midwife,moh)
            );

            for (Employee employee : employees) {
                if (employeeRepository.findByUserEmail(employee.getUser().getEmail()).isEmpty()) {
                    employee.setRegion(region);
                    employee.setCreatedBy(adminUser);
                    employee.setUpdatedBy(adminUser);
                    employee.setCreatedAt(LocalDateTime.now());
                    employee.setUpdatedAt(LocalDateTime.now());
//                    employeeRepository.save(employee);
                    log.info("Employee {} created successfully", employee.getUser().getRole());
                } else {
                    log.warn("Employee {} already exists.", employee.getUser().getRole());
                }
            }
        };
    }
}