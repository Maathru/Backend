package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.service.ClinicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clinic")
@RequiredArgsConstructor
@Validated
public class ClinicController {
    private final ClinicService clinicService;

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
}
