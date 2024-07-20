package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.CreateUserDto;
import com.maathru.backend.Application.dto.response.MidwifeResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.service.EmployeeService;
import com.maathru.backend.Domain.service.RegionService;
import com.maathru.backend.Domain.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            description = "Get Single User",
            summary = "Get user by specific id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    @GetMapping()
    public ResponseEntity<UserProfileDto> getUser(@RequestParam long id) {
        return userService.getUser(id);
    }

    @Operation(
            description = "Create User",
            summary = "Post method to create new",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody CreateUserDto createUserDto) {
        return userService.addUser(createUserDto);
    }

    @GetMapping("/{province}/{district}/{area}")
    public ResponseEntity<List<RegionResponse>> findRegions(
            @PathVariable String province,
            @PathVariable String district,
            @PathVariable String area) {
        return regionService.getRegionNamesByMOHAttributes(province, district, area);
    }

    @GetMapping("/midwife/{regionId}")
    public ResponseEntity<MidwifeResponse> findMidwifeByRegionId(@PathVariable long regionId) {
        return employeeService.getMidwifeByRegionId(regionId);
    }
}
