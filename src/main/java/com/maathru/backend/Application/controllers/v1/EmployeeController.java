package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.EmployeeDto;
import com.maathru.backend.Application.dto.response.DoctorsResponse;
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

//    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
//    @GetMapping("/doctor/{district}/{area}")
//    public ResponseEntity<List<DoctorsResponse>> getDoctorsByMohAreaAndDistrict(
//            @PathVariable String district,
//            @PathVariable String area) {
//        return employeeService.getDoctorsByMohAreaAndDistrict(district,area);
//    }
}
