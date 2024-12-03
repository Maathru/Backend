package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.response.*;
import com.maathru.backend.Domain.service.ClinicService;
import com.maathru.backend.Domain.service.EmployeeService;
import com.maathru.backend.Domain.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/admin/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToAdmin(@PathVariable String date) {
        return clinicService.getClinicsByDateToAdmin(date);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToDoctor(@PathVariable String date) {
        return clinicService.getClinicsByDateToDoctor(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDateToMidwife(@PathVariable String date) {
        return clinicService.getClinicsByDateToMidwife(date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/month/clinic/admin/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonthForAdmin(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForAdmin(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/month/clinic/midwife/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonthForMidwife(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForMidwife(date);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/month/parent/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForParent(@PathVariable String date) {
        return clinicService.getClinicsDatesGivenMonthForParent(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/month/midwife/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForMidwife(@PathVariable String date) {
        return clinicService.getClinicsDatesGivenMonthForMidwife(date);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/month/doctor/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsDatesGivenMonthForDoctor(@PathVariable String date) {
        return clinicService.getClinicsDatesGivenMonthForDoctor(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/upcoming/midwife")
    public ResponseEntity<List<ClinicResponse>> getUpcomingClinicsForMidwife() {
        return clinicService.getUpcomingClinicsForMidwife();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/upcoming/doctor")
    public ResponseEntity<List<ClinicResponse>> getUpcomingClinicsForDoctor() {
        return clinicService.getUpcomingClinicsForDoctor();
    }
}