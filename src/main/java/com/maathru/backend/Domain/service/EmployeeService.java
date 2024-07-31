package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.MidwifeListResponse;
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
    private final JwtService jwtService;

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
        Employee employee = employeeRepository.findByUserRoleAndRegionId(Role.MIDWIFE, regionId).orElseThrow(() -> new NotFoundException("Midwife not found in this region"));

        MidwifeResponse midwifeResponse = new MidwifeResponse();
        midwifeResponse.setId(employee.getUser().getUserId());
        midwifeResponse.setName(employee.getUser().getFirstName() + " " + employee.getUser().getLastName());
        midwifeResponse.setEmail(employee.getUser().getEmail());
        midwifeResponse.setPhone(employee.getPhoneNumber());
        midwifeResponse.setAddress(employee.getAddressLine1() + ", " + employee.getStreet() + ", " + employee.getCity());

        return ResponseEntity.status(200).body(midwifeResponse);
    }

    public ResponseEntity<List<MidwifeListResponse>> getMidwifesInCurrentMoh() {
        User user = jwtService.getCurrentUser();

        List<MidwifeListResponse> midwifeListResponses = employeeRepository.findMidwifesByUserAndRole(user, Role.MIDWIFE);

        if (midwifeListResponses.isEmpty()) {
            log.error("MIDWIFE list is empty in this region");
            throw new NotFoundException("MIDWIFE list is empty in this region");
        }

        return ResponseEntity.status(200).body(midwifeListResponses);
    }
}
