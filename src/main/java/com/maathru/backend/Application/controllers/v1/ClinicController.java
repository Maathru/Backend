package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.response.*;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.service.ClinicService;
import com.maathru.backend.Domain.service.EmployeeService;
import com.maathru.backend.Domain.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clinic")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClinicController {
    private final ClinicService clinicService;
    private final RegionService regionService;
    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> createOrUpdateClinic(@RequestBody @Valid ClinicDto clinicDto) {
        return clinicService.createOrUpdateClinic(clinicDto);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ClinicResponse> getClinic(@PathVariable Long clinicId) {
        return clinicService.getClinic(clinicId);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Clinic>> getAllClinics() {
        return clinicService.getAllClinics();
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<Clinic> deleteClinic(@PathVariable Long clinicId) {
        return clinicService.deleteClinic(clinicId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/regions")
    public ResponseEntity<List<RegionResponse>> getRegions() {
        return regionService.getRegionsForClinics();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorsResponse>> getDoctors() {
        return employeeService.getDoctorsForClinics();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDate(@PathVariable String date) {
        return clinicService.getClinicsByDate(date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/month/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonth(@PathVariable String date) {
        return clinicService.getClinicsGivenMonth(date);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/month/parent/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonthForParent(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForParent(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/month/midwife/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonthForMidwife(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForMidwife(date);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/month/doctor/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonthForDoctor(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForDoctor(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/upcoming/midwife")
    public ResponseEntity<List<ClinicDateAndNameResponse>> getUpcomingClinicsForMidwife() {
        log.info("Here");
        return clinicService.getUpcomingClinicsForMidwife();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/upcoming/doctor")
    public ResponseEntity<List<ClinicResponse>> getUpcomingClinicsForDoctor() {
        log.info("Here");
        return clinicService.getUpcomingClinicsForDoctor();
    }
}