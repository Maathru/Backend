package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.MidwifeListResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.service.EmployeeService;
import com.maathru.backend.Domain.service.RegionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
@AllArgsConstructor
public class RegionController {
    private final RegionService regionService;
    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<RegionResponse>> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDto> getRegion(@PathVariable long id) {
        return regionService.getRegion(id);
    }

    @GetMapping("/midwife")
    public ResponseEntity<List<DoctorsResponse>> getMidwifesInCurrentMohForRegions() {
        return employeeService.getMidwifesInCurrentMohForRegions();
    }

    @PostMapping()
    public ResponseEntity<String> addOrUpdateRegion(@RequestBody @Valid RegionDto regionDto) {
        return regionService.addOrUpdateRegion(regionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable long id) {
        return regionService.deleteRegion(id);
    }

}
