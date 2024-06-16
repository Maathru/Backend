package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.CreateUserDto;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Tag(name = "User Controller")
public class UserController {
    private final UserService userService;

    @Hidden
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
}
