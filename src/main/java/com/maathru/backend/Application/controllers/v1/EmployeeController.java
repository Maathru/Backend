package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.AdminDashboard;
import com.maathru.backend.Application.dto.response.HomeVisitsResponse;
import com.maathru.backend.Application.dto.response.MidwifeDashboard;
import com.maathru.backend.Application.dto.response.MidwifeListResponse;
import com.maathru.backend.Domain.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
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

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/home-visits/{id}")
    public ResponseEntity<HomeVisitsResponse> getMidwifeHomeVisitsData(@PathVariable long id) {
        return employeeService.getMidwifeHomeVisitsData(id);
    }
}
