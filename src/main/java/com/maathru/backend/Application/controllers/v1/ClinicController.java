package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.service.ClinicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clinic")
@AllArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;

    @PostMapping()
    public ResponseEntity<Clinic> createClinic(@RequestBody ClinicDto clinicDto) {
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

    @PutMapping("/{clinicId}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable Long clinicId, @RequestBody ClinicDto clinicDto) {
        return clinicService.updateClinic(clinicId, clinicDto);
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<Clinic> deleteClinic(@PathVariable Long clinicId) {
        return clinicService.deleteClinic(clinicId);
    }
}
