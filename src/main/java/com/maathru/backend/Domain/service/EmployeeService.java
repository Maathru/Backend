package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            throw new UserNotFoundException("User not found");
        }

    }
}
