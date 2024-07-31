package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.service.ClinicService;
import com.maathru.backend.Domain.service.RegionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> createClinic(@RequestBody @Valid ClinicDto clinicDto) {
        return clinicService.createClinic(clinicDto);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<Clinic> getClinic(@PathVariable Long clinicId) {
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
        return regionService.getRegions();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorsResponse>> getDoctors() {
        return regionService.getDoctors();
    }

    @GetMapping("/by/{date}")
    public ResponseEntity<List<ClinicListResponse>> getClinicsByDate(@PathVariable String date) {
        return clinicService.getClinicsByDate(date);
    }

    @GetMapping("/month/{date}")
    public ResponseEntity<List<LocalDate>> getClinicsGivenMonth(@PathVariable String date) {
        return clinicService.getClinicsGivenMonth(date);
    }
}
