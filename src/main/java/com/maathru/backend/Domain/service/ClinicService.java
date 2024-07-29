package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.Domain.exception.*;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.External.utils.TimeUtils;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;

    public ResponseEntity<String> createClinic(ClinicDto clinicDto) {
        try {
            User currentUser = jwtService.getCurrentUser();


            Region region = regionRepository.findById(clinicDto.getRegion()).orElseThrow(() -> new NotFoundException("Region not found"));

            List<Employee> doctors = clinicDto.getDoctors().stream()
                    .map(doctorResponse -> employeeRepository.findByEmployeeIdAndUserRole(doctorResponse.getId(), Role.DOCTOR)
                            .orElseThrow(() -> new NotFoundException("Employee not found: " + doctorResponse.getId())))
                    .toList();

            // Adjust times before validation
            clinicDto.setStartTime(TimeUtils.adjustTime(clinicDto.getStartTime()));
            clinicDto.setEndTime(TimeUtils.adjustTime(clinicDto.getEndTime()));

            Clinic clinic = new Clinic();

            clinic.setName(clinicDto.getName());
            clinic.setRegion(region);
            clinic.setDate(clinicDto.getDate());
            clinic.setStartTime(LocalTime.from(clinicDto.getStartTime()));
            clinic.setEndTime(LocalTime.from(clinicDto.getEndTime()));
            clinic.setOther(clinicDto.getOther());
            clinic.setDoctors(doctors);
            clinic.setCreatedBy(currentUser);
            clinic.setUpdatedBy(currentUser);

            clinic = clinicRepository.save(clinic);

            log.info("Clinic added successfully with ID: {}", clinic.getClinicId());
            return ResponseEntity.status(201).body("Clinic ");
        } catch (Exception e) {
            log.error("Error creating clinic");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating clinic");
        }
    }

    public ResponseEntity<Clinic> getClinic(Long clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);

        if (optionalClinic.isPresent()) {
            return ResponseEntity.ok(optionalClinic.get());
        } else {
            log.error("Clinic not found");
            throw new NotFoundException("Clinic not found");
        }
    }

    public ResponseEntity<Iterable<Clinic>> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();

        if (clinics.isEmpty()) {
            log.error("Clinics not found");
            throw new NotFoundException("Clinics not found");
        }
        return ResponseEntity.ok(clinics);
    }


    public ResponseEntity<Clinic> deleteClinic(Long clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);

        if (optionalClinic.isPresent()) {
            clinicRepository.delete(optionalClinic.get());
            return ResponseEntity.ok().body(optionalClinic.get());
        } else {
            log.error("Clinic not found");
            throw new NotFoundException("Clinic not found");
        }
    }
}
