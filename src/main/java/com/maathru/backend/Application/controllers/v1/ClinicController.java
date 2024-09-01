package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.ClinicResponse;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Clinic;
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

    @GetMapping()
    public ResponseEntity<Iterable<Clinic>> getAllClinics() {
        return clinicService.getAllClinics();
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<Clinic> deleteClinic(@PathVariable Long clinicId) {
        return clinicService.deleteClinic(clinicId);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/regions")
    public ResponseEntity<List<RegionResponse>> getRegions() {
        return regionService.getRegionsForClinics();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorsResponse>> getDoctors() {
        return employeeService.getDoctorsForClinics();
    }

    @GetMapping("/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDate(@PathVariable String date) {
        return clinicService.getClinicsByDate(date);
    }

    @GetMapping("/month/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsGivenMonth(@PathVariable String date) {
        return clinicService.getClinicsGivenMonth(date);
    }

    @GetMapping("/month/parent/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonthForParent(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForParent(date);
    }

    @GetMapping("/month/midwife/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonthForMidwife(@PathVariable String date) {
        return clinicService.getClinicsGivenMonthForMidwife(date);
    }
}
