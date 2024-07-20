package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.exception.InvalidException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.validation.impl.LocationValidator;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RegionService {
    private final RegionRepository regionRepository;
    private final ModelMapper mapper;

    public ResponseEntity<List<RegionResponse>> getRegionNamesByMOHAttributes(String province, String district, String area) {
        if (!LocationValidator.isValidRegion(province, district, area)) {
            throw new InvalidException("Invalid province, district, or area.");
        }

        Province provinceEnum;
        District districtEnum;
        Area areaEnum;

        try {
            provinceEnum = Province.valueOf(province.toUpperCase());
            districtEnum = District.valueOf(district.toUpperCase());
            areaEnum = Area.valueOf(area.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidException("Invalid enum value for province, district, or area.");
        }

        List<Region> regions = regionRepository.findRegionsByMOHAttributes(provinceEnum, districtEnum, areaEnum);
        List<RegionResponse> regionResponses = regions
                .stream()
                .map(region -> mapper.map(region, RegionResponse.class))
                .collect(Collectors.toList());

        if (regions.isEmpty()) {
            throw new NotFoundException("No regions found for these details");
        }
        return ResponseEntity.status(201).body(regionResponses);
    }
}
