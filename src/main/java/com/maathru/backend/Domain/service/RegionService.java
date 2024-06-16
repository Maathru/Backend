package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.MOHRepository;
import com.maathru.backend.External.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final MOHRepository mohRepository;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Region> createRegion(RegionDto regionDto) {
        Region region = new Region();
        region.setRegionNumber(regionDto.getRegionNumber());
        region.setRegionName(regionDto.getRegionName());
        region.setPopulation(regionDto.getPopulation());
        region.setMoh(mohRepository.findById(regionDto.getMohMohId()).orElse(null));
        regionDto.getMidwifeId().forEach(midwifeId -> region.getMidwives().add(employeeRepository.findById(midwifeId).orElse(null)));
        return ResponseEntity.ok(regionRepository.save(region));
    }
}
