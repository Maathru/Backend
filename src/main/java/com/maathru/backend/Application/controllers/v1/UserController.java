package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.CreateUserDto;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping()
    public ResponseEntity<UserProfileDto> getUser(@RequestParam long id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody CreateUserDto createUserDto) {
        return userService.addUser(createUserDto);
    }
}
