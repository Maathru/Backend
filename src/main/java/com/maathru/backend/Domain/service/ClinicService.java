package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.eligible.PastPregnancyDTO;
import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.request.HomeVisitRequest;
import com.maathru.backend.Application.dto.request.VisitDto;
import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.ClinicResponse;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.Domain.exception.*;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.External.utils.TimeUtils;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final HomeVisitRepository homeVisitRepository;
    private final ClinicRepository clinicRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

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

    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToAdmin(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<ClinicListResponse> clinicListResponses = clinicRepository.findClinicsByDateToAdmin(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.error("Clinics not found for date {}", date);
            throw new NotFoundException("Clinics not found for date " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToMidwife(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<Clinic> clinics = clinicRepository.findClinicsByDateToMidwife(localDate, currentUser.getEmail());
        List<ClinicListResponse> clinicListResponses = clinics.stream()
                .map(clinic -> new ClinicListResponse(
                        clinic.getClinicId(),
                        clinic.getName(),
                        clinic.getStartTime(),
                        clinic.getEndTime(),
                        clinic.getDoctors().stream()
                                .map(doctor -> doctor.getUser().getFirstName() + " " + doctor.getUser().getLastName())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        if (clinicListResponses.isEmpty()) {
            log.error("Clinics not found for date {}", date);
            throw new NotFoundException("Clinics not found for date " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToDoctor(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<ClinicListResponse> clinicListResponses = clinicRepository.findClinicsByDateToDoctor(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for date {}", date);
            throw new NotFoundException("Clinics not found for date " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonthForAdmin(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<ClinicListResponse> clinicListResponses = clinicRepository.findClinicsByMonthForAdmin(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonthForMidwife(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<Clinic> clinics = clinicRepository.findClinicsByMonthForMidwife(localDate, currentUser.getEmail());
        List<ClinicListResponse> clinicListResponses = clinics.stream()
                .map(clinic -> new ClinicListResponse(
                        clinic.getClinicId(),
                        clinic.getName(),
                        clinic.getDate(),
                        clinic.getStartTime(),
                        clinic.getEndTime(),
                        clinic.getDoctors().stream()
                                .map(doctor -> doctor.getUser().getFirstName() + " " + doctor.getUser().getLastName())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForParent(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<LocalDate> clinicListResponses = clinicRepository.findAllClinicDatesForCurrentMonthByParent(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForMidwife(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<LocalDate> clinicListResponses = clinicRepository.findAllClinicDatesForCurrentMonthByMidwife(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicResponse>> getUpcomingClinicsForMidwife() {
        User currentUser = jwtService.getCurrentUser();

        List<ClinicResponse> clinicListResponses = clinicRepository.findUpcomingClinicsForMidwife(currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("No any upcoming clinics");
            throw new NotFoundException("No any upcoming clinics");
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForDoctor(String date) {
        User currentUser = jwtService.getCurrentUser();

        LocalDate localDate = LocalDate.parse(date);
        List<LocalDate> clinicListResponses = clinicRepository.findAllClinicDatesForCurrentMonthByDoctor(localDate, currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("Clinics not found for this month {}", date);
            throw new NotFoundException("Clinics not found for this month " + date);
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    public ResponseEntity<List<ClinicResponse>> getUpcomingClinicsForDoctor() {
        User currentUser = jwtService.getCurrentUser();

        List<ClinicResponse> clinicListResponses = clinicRepository.findUpcomingClinicsForDoctor(currentUser.getEmail());

        if (clinicListResponses.isEmpty()) {
            log.warn("No any upcoming clinics");
            throw new NotFoundException("No any upcoming clinics");
        }
        return ResponseEntity.ok(clinicListResponses);
    }

    @Transactional
    public ResponseEntity<String> saveOrUpdateHomeVisits(HomeVisitRequest homeVisitRequest) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user = userRepository.findById(homeVisitRequest.getId())
                    .orElseThrow(() -> new NotFoundException("User not found"));

            HomeVisit homeVisit = homeVisitRepository.findByUser(user).orElseGet(HomeVisit::new);
            homeVisit.setUser(user);

            if (homeVisit.getId() == 0) {
                homeVisit.setCreatedBy(currentUser);
            }
            homeVisit.setUpdatedBy(currentUser);

            // Clear existing visits and add new ones
            homeVisit.getVisits().clear();
            List<Visit> visits = homeVisitRequest.getVisits().stream()
                    .map(visitDto -> {
                        Visit visit = new Visit();
                        visit.setDate(visitDto.getDate());
                        visit.setTime(visitDto.getTime());
                        visit.setStatus(visitDto.getStatus());
                        visit.setHomeVisit(homeVisit);
                        return visit;
                    })
                    .toList();
            homeVisit.getVisits().addAll(visits);

            homeVisitRepository.save(homeVisit);
            log.info("Home visits saved successfully for user: {}", user.getEmail());
            return ResponseEntity.ok("Home visits saved successfully!");
        } catch (NotFoundException e) {
            log.error("User not found: {}", homeVisitRequest.getId());
            throw e;
        } catch (Exception e) {
            log.error("Error saving home visits for user: {} - {}", jwtService.getCurrentUser().getEmail(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving home visits");
        }
    }


    @Transactional(readOnly = true)
    public ResponseEntity<HomeVisitRequest> getHomeVisits(long userId) {
        try {
            // Fetch the user or throw exception if not found
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

            // Fetch the home visits for the user or throw exception if not found
            HomeVisit homeVisit = homeVisitRepository.findByUser(user)
                    .orElseThrow(() -> new NotFoundException("Home visits not found for user id: " + userId));

            // Map visits to VisitDto
            List<VisitDto> visits = homeVisit.getVisits().stream()
                    .map(visit -> {
                        VisitDto visitDto = new VisitDto();
                        visitDto.setDate(visit.getDate());
                        visitDto.setTime(visit.getTime());
                        visitDto.setStatus(visit.getStatus());
                        return visitDto;
                    })
                    .collect(Collectors.toList());

            // Create and populate the response DTO
            HomeVisitRequest homeVisitRequest = new HomeVisitRequest();
            homeVisitRequest.setVisits(visits);
            homeVisitRequest.setId(userId);

            return ResponseEntity.ok(homeVisitRequest);
        } catch (NotFoundException e) {
            log.error("Error fetching home visits: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error fetching home visits for user id {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}