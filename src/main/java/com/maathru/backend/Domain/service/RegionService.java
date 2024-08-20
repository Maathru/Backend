package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.Domain.exception.ApiException;
import com.maathru.backend.Domain.exception.InvalidException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.SQLException;
import com.maathru.backend.Domain.validation.impl.LocationValidator;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.MOHRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import com.maathru.backend.enumeration.Role;
import jakarta.transaction.Transactional;
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
    private final MOHRepository mohRepository;

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

    public ResponseEntity<List<RegionResponse>> getRegionsForClinics() {
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

    public ResponseEntity<List<RegionResponse>> getAllRegions() {
        try {
            User user = jwtService.getCurrentUser();
            List<RegionResponse> regions = regionRepository.findRegionsByAdmin(user.getEmail(), Role.MIDWIFE);
            return ResponseEntity.status(201).body(regions);
        } catch (Exception e) {
            log.error("Error retrieving current user regions {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public ResponseEntity<String> addRegion(RegionDto regionDto) {

        User currentUser = jwtService.getCurrentUser();
        Employee currentEmployee = employeeRepository.findByUser(currentUser).orElseThrow(() -> new NotFoundException("Employee not found"));
        MOH moh = mohRepository.findById(currentEmployee.getMoh().getMohId()).orElseThrow(() -> new NotFoundException("MOH not found"));

        Region region = mapper.map(regionDto, Region.class);
        region.setCreatedBy(currentUser);
        region.setUpdatedBy(currentUser);
        region.setMoh(moh);
        region = regionRepository.save(region);
        log.info("Region: {} added successfully by {}", region.getRegionId(), currentUser.getEmail());

        if (regionDto.getMidwife() != null || regionDto.getMidwife() != 0) {
            Employee midwife = employeeRepository.findByEmployeeIdAndUserRole(regionDto.getMidwife(), Role.MIDWIFE).orElseThrow(() -> new NotFoundException("Midwife not found"));
            midwife.setRegion(region);
            midwife.setUpdatedBy(currentUser);
            employeeRepository.save(midwife);
            log.info("Midwife: {} assigned successfully by {}", midwife.getUser().getEmail(), currentUser.getEmail());
        }
        return ResponseEntity.status(201).body("Region added successfully");
    }

    public ResponseEntity<String> deleteRegion(long id) {
        try {
            Region region = regionRepository.findById(id).orElseThrow(() -> new NotFoundException("Region:" + id + " not found"));
            regionRepository.delete(region);
            log.info("Region:{} deleted by {}", id, jwtService.getCurrentUser().getEmail());
            return ResponseEntity.ok("Region:" + id + " deleted successfully");

        } catch (NotFoundException e) {
            log.error("An error occurred: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            throw new ApiException("Cannot delete region until changing users assigned to this region");
        }
    }
}