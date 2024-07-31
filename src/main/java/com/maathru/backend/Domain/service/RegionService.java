package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.InvalidException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.validation.impl.LocationValidator;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RegionService {
    private final JwtService jwtService;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public ResponseEntity<List<RegionResponse>> getRegionNamesByMOHAttributes(String province, String district, String area) {
        if (!LocationValidator.isValidRegionByProvinceAndDistrict(province, district, area)) {
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

    public ResponseEntity<List<RegionResponse>> getRegions() {
        try {
            User user = jwtService.getCurrentUser();
            List<RegionResponse> regions = regionRepository.findRegionsByUser(user.getEmail());

            return ResponseEntity.status(201).body(regions);
        } catch (Exception e) {
            log.error("Error retrieving current user regions {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<DoctorsResponse>> getDoctors() {
        try {
            User user = jwtService.getCurrentUser();
            List<DoctorsResponse> doctors = employeeRepository.findEmployeesByUserAndRole(user, Role.DOCTOR);

            return ResponseEntity.status(201).body(doctors);
        } catch (Exception e) {
            log.error("Error retrieving current user moh doctors {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
