package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.AdminDashboard;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Application.dto.response.MidwifeDashboard;
import com.maathru.backend.Application.dto.response.MidwifeListResponse;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/midwife")
    public ResponseEntity<List<MidwifeListResponse>> getMidwifesInCurrentMoh() {
        return employeeService.getMidwifesInCurrentMoh();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/admin")
    public ResponseEntity<AdminDashboard> getAdminDashboardData() {
        return employeeService.getAdminDashboardData();
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/dashboard/midwife")
    public ResponseEntity<MidwifeDashboard> getMidwifeDashboardData() {
        return employeeService.getMidwifeDashboardData();
    }
}
