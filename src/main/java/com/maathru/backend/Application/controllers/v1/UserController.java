package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.response.AdminDashboard;
import com.maathru.backend.Application.dto.response.MidwifeResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.service.EmployeeService;
import com.maathru.backend.Domain.service.RegionService;
import com.maathru.backend.Domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
@Slf4j
public class UserController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final RegionService regionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/get/{email}")
    public ResponseEntity<Long> getUserIdByEmailForMidwife(@PathVariable String email) {
        return userService.getUserIdByEmailForMidwife(email);
    }

    @PreAuthorize("hasAnyRole('USER','ELIGIBLE','PARENT','DOCTOR','ADMIN')")
    @GetMapping("/{province}/{district}/{area}")
    public ResponseEntity<List<RegionResponse>> findRegions(
            @PathVariable String province,
            @PathVariable String district,
            @PathVariable String area) {
        return regionService.getRegionNamesByMOHAttributes(province, district, area);
    }

    @PreAuthorize("hasAnyRole('USER','ELIGIBLE','PARENT')")
    @GetMapping("/midwife/{regionId}")
    public ResponseEntity<MidwifeResponse> findMidwifeByRegionId(@PathVariable long regionId) {
        return employeeService.getMidwifeByRegionId(regionId);
    }
}
