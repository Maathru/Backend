package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Domain.entity.MOH;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.exception.MOHNotFoundException;
import com.maathru.backend.External.repository.MOHRepository;
import com.maathru.backend.External.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RegionService {
    private final RegionRepository regionRepository;
    private final MOHRepository mohRepository;

    public ResponseEntity<Region> createRegion(RegionDto regionDto) {
        Optional<MOH> optionalMOH = mohRepository.findById(regionDto.getMoh());

        if (optionalMOH.isPresent()) {
            Region region = new Region();
            region.setRegionNumber(regionDto.getRegionNumber());
            region.setRegionName(regionDto.getRegionName());
            region.setPopulation(regionDto.getPopulation());
            region.setMoh(optionalMOH.get());
            region = regionRepository.save(region);

            return ResponseEntity.status(201).body(region);
        } else {
            log.error("MOH not found");
            throw new MOHNotFoundException("MOH not found");
        }
    }
}
