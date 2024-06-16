package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Employee> createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setNic(employeeDto.getNic());
        employee.setAddress(employeeDto.getAddress());
        employee.setGender(employeeDto.getGender());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setQualifications(employeeDto.getQualifications());
        employee.setUser(userRepository.findById(employeeDto.getUserId()).orElse(null));
        return ResponseEntity.ok(employeeRepository.save(employee));

    }
}
