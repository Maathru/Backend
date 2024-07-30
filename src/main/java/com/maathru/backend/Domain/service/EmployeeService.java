package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.MidwifeResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.InvalidException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.validation.impl.LocationValidator;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Employee> createEmployee(EmployeeDto employeeDto) {
        Optional<User> optionalUser = userRepository.findById(employeeDto.getUser());

        if (optionalUser.isPresent()) {
            Employee employee = new Employee();
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setNic(employeeDto.getNic());
            employee.setAddressLine1(employeeDto.getAddressLine1());
            employee.setStreet(employeeDto.getStreet());
            employee.setCity(employeeDto.getCity());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setQualifications(employeeDto.getQualifications());
            employee.setUser(optionalUser.get());
            employee = employeeRepository.save(employee);

            return ResponseEntity.status(201).body(employee);
        } else {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

    }

    public ResponseEntity<MidwifeResponse> getMidwifeByRegionId(long regionId) {
        return null;
    }

    public ResponseEntity<List<DoctorsResponse>> getDoctorsByMohAreaAndDistrict(String district, String area) {
        if (!LocationValidator.isValidRegionByDistrict(district, area)) {
            throw new InvalidException("Invalid district, or area.");
        }

        District districtEnum;
        Area areaEnum;

        try {
            districtEnum = District.valueOf(district.toUpperCase());
            areaEnum = Area.valueOf(area.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidException("Invalid enum value for district, or area.");
        }

        List<DoctorsResponse> doctorsResponses = employeeRepository.findEmployeesByDistrictAndRegion(districtEnum, areaEnum, Role.DOCTOR);

        if (doctorsResponses.isEmpty()) {
            throw new NotFoundException("No doctor found for these details");
        }

        return ResponseEntity.status(201).body(doctorsResponses);
    }
}
