package com.maathru.backend.Application.controllers;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

}
