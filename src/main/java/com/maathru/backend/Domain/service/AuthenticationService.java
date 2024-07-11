package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.response.AuthenticationResponse;
import com.maathru.backend.Domain.entity.Token;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.External.repository.TokenRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLoginAttempts(0);
        user.setRole(Role.USER); // Default Role User
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setEnabled(true);

        user = userRepository.save(user);

        // generate tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // save the generated token
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse("User signed up Successfully");
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);

        // save the generated token
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(user.getUserId(), user.getFirstName(), accessToken, refreshToken, user.getRole(), "User Logged in Successfully");
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Invalid Bearer Token");
        }

        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (jwtService.isValidRefreshToken(token, user)) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new AuthenticationResponse(accessToken, refreshToken, "Token Refreshed Successfully");
        }
        throw new UnauthorizedException("Invalid Jwt Token");
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokenListByUser = tokenRepository.findAllAccessTokensByUser(user.getUserId());
        if (!validTokenListByUser.isEmpty()) {
            validTokenListByUser.forEach(token -> {
                token.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
}
