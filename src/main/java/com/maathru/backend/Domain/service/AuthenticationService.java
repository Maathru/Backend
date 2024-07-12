package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.SigninDto;
import com.maathru.backend.Application.dto.request.SignupDto;
import com.maathru.backend.Application.dto.response.AuthenticationResponse;
import com.maathru.backend.Domain.entity.Token;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.AuthenticationException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.TokenRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ModelMapper mapper;

    public AuthenticationResponse signup(SignupDto signupDto) {
        User currentUser = getCurrentUser();

        User user = mapper.map(signupDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginAttempts(0);

        if (currentUser.getUserId() != 0) {
            user.setCreatedBy(currentUser);
            user.setUpdatedBy(currentUser);
        }

        // Get users role
        user.setRole(createNewUserRole(currentUser, user));

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);

        user = userRepository.save(user);
        if (currentUser.getUserId() != 0) {
            log.info("User signed up successfully: {} by {}: {}", user.getUsername(), currentUser.getRole(), currentUser.getUserId());
        } else {
            log.info("User signed up successfully: {} by the user themselves", user.getUsername());
        }

        return new AuthenticationResponse("User signed up Successfully");
    }

    public AuthenticationResponse signin(SigninDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getLoginAttempts() != null && user.getLoginAttempts() >= 5) {
            throw new AuthenticationException("Account is locked due to too many failed login attempts");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            // Increment login attempts on authentication failure
            if (user.getLoginAttempts() == null) {
                user.setLoginAttempts(1);
            } else {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
            }

            // Lock the account if login attempts to exceed 5
            if (user.getLoginAttempts() >= 5) {
                user.setAccountNonLocked(false);
            }

            userRepository.save(user);

            throw new AuthenticationException("Invalid email or password");
        } catch (DisabledException e) {
            throw new AuthenticationException("Account is locked");
        }

        // Reset login attempts on successful login
        user.setLoginAttempts(0);
        user.setAccountNonLocked(true);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);

        // Save the generated token
        saveUserToken(accessToken, refreshToken, user);

        // Save last logged in time
        user.setLastLogin(now());
        userRepository.save(user);

        log.info("{}:{} signed in successfully", user.getRole(), user.getUsername());

        return new AuthenticationResponse(user.getUserId(), user.getFirstName(), accessToken, refreshToken, user.getRole(), "User logged in successfully");
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Invalid Bearer Token");
        }

        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));

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

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            return optionalUser.orElseGet(User::new);
        }
        return new User();
    }

    private static Role createNewUserRole(User currentUser, User user) {
        Role newRole = Role.USER; // Default to USER

        if (currentUser.getUserId() != 0 && user.getRole() != null) {
            if (currentUser.getRole() == Role.ADMIN) {
                // Admins can set any role, so keep the desired role
                newRole = user.getRole();
            } else if (currentUser.getRole() == Role.DOCTOR && user.getRole() == Role.MIDWIFE) {
                // Doctors can only set the role to MIDWIFE
                newRole = Role.MIDWIFE;
            } else if (currentUser.getRole() == Role.MIDWIFE) {
                // Midwives can set the role to PARENT, ELIGIBLE, PENDING, or USER
                if (user.getRole() == Role.PARENT || user.getRole() == Role.ELIGIBLE || user.getRole() == Role.PENDING || user.getRole() == Role.USER) {
                    newRole = user.getRole();
                }
            }
        }
        return newRole;
    }
}
