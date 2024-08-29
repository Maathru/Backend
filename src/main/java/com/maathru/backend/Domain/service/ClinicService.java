package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.ClinicResponse;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.Domain.exception.*;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.External.utils.TimeUtils;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final ModelMapper mapper;

    @Transactional
    public ResponseEntity<String> createOrUpdateClinic(ClinicDto clinicDto) {
        try {
            User currentUser = jwtService.getCurrentUser();

            Region region = regionRepository.findById(clinicDto.getRegion())
                    .orElseThrow(() -> new NotFoundException("Region not found"));

            Clinic clinic = clinicRepository.findClinicWithDoctorsById(clinicDto.getClinicId(), currentUser.getEmail())
                    .orElseGet(Clinic::new);

            // Adjust times before validation
            clinicDto.setStartTime(TimeUtils.adjustTime(clinicDto.getStartTime()));
            clinicDto.setEndTime(TimeUtils.adjustTime(clinicDto.getEndTime()));

            clinic.setName(clinicDto.getName());
            clinic.setRegion(region);
            clinic.setDate(clinicDto.getDate());
            clinic.setStartTime(LocalTime.from(clinicDto.getStartTime()));
            clinic.setEndTime(LocalTime.from(clinicDto.getEndTime()));
            clinic.setOther(clinicDto.getOther());
            clinic.setMoh(region.getMoh());

            if (clinicDto.getDoctors() != null) {
                List<Employee> doctors = clinicDto.getDoctors().stream()
                        .map(doctorResponse -> employeeRepository.findByEmployeeIdAndUserRole(doctorResponse.getId(), Role.DOCTOR)
                                .orElseThrow(() -> new NotFoundException("Employee not found: " + doctorResponse.getId())))
                        .collect(Collectors.toList());
                clinic.setDoctors(doctors);
            } else {
                throw new NotFoundException("Doctors not found");
            }

            if (clinic.getClinicId() > 0) {
                clinic.setCreatedBy(currentUser);
            }
            clinic.setUpdatedBy(currentUser);

            clinic = clinicRepository.save(clinic);

            log.info("Clinic added/updated successfully with ID: {}", clinic.getClinicId());
            return ResponseEntity.status(HttpStatus.CREATED).body("Clinic added/updated successfully");
        } catch (NotFoundException e) {
            log.error("NotFoundException creating clinic: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error creating clinic: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating clinic");
        }
    }

    public ResponseEntity<ClinicResponse> getClinic(Long clinicId) {
        User currentUser = jwtService.getCurrentUser();
        Clinic clinic = clinicRepository.findClinicWithDoctorsById(clinicId, currentUser.getEmail())
                .orElseThrow(() -> new NotFoundException("Clinic not found"));

        // Map Clinic entity to ClinicResponse DTO
        ClinicResponse clinicResponse = new ClinicResponse();
        clinicResponse.setClinicId(clinic.getClinicId());
        clinicResponse.setName(clinic.getName());
        clinicResponse.setDate(clinic.getDate());
        clinicResponse.setStartTime(clinic.getStartTime());
        clinicResponse.setEndTime(clinic.getEndTime());
        clinicResponse.setRegion(clinic.getRegion().getRegionId());
        clinicResponse.setOther(clinic.getOther());

        // Map Employee entities to DoctorsResponse DTOs
        List<DoctorsResponse> doctorsResponses = clinic.getDoctors().stream().map(doctor -> {
            DoctorsResponse doctorsResponse = new DoctorsResponse();
            doctorsResponse.setId(doctor.getEmployeeId());
            doctorsResponse.setName(doctor.getUser().getFirstName() + " " + doctor.getUser().getLastName());
            return doctorsResponse;
        }).collect(Collectors.toList());

        clinicResponse.setDoctors(doctorsResponses);

        return ResponseEntity.ok(clinicResponse);
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

    public ResponseEntity<List<ClinicListResponse>> getClinicsByDate(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<ClinicListResponse> clinicListResponses = clinicRepository.findClinicsByDate(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.error("Clinics not found for date {}", date);
            throw new NotFoundException("Clinics not found for date " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonth(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<ClinicListResponse> clinicListResponses = clinicRepository.findClinicsByMonth(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.error("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }
}
