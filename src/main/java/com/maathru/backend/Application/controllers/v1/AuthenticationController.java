package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.response.AuthenticationResponse;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(HttpServletRequest request, HttpServletResponse response) {
        return authenticationService.refreshToken(request, response);
    }
}
