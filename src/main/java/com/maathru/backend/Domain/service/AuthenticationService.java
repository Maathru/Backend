package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.SigninDto;
import com.maathru.backend.Application.dto.request.SignupDto;
import com.maathru.backend.Application.dto.request.TokenRequest;
import com.maathru.backend.Application.dto.response.AuthenticationResponse;
import com.maathru.backend.Domain.entity.Token;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.ApiException;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.External.repository.TokenRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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

import static com.maathru.backend.External.utils.PasswordUtils.generatePassword;
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
    private final EmailService emailService;

    @Transactional
    public AuthenticationResponse signup(SignupDto signupDto) {
        try {
            User currentUser = getCurrentUser();
            User user = mapper.map(signupDto, User.class);

            encodePassword(user);
            initializeUserFields(user, currentUser);
            user.setRole(determineUserRole(currentUser, user));


            user = userRepository.save(user);

            if (currentUser.getUserId() != 0) {
                log.info("User signed up successfully: {} by {}: {}", user.getUsername(), currentUser.getRole(), currentUser.getUserId());
            } else {
                log.info("User signed up successfully: {} by the user themselves", user.getUsername());
            }

            return new AuthenticationResponse(user.getUserId() + "/User signed up Successfully");
        } catch (Exception e) {
            log.error("Error during user signup: {}", e.getMessage());
            throw new ApiException("An error occurred during user signup");
        }
    }

    @Transactional
    public AuthenticationResponse signin(SigninDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));

        checkAccountLock(user);

        try {
            authenticateUser(request.getEmail(), request.getPassword());
        } catch (BadCredentialsException e) {
            handleFailedLoginAttempt(user);
            throw new UnauthorizedException("Invalid email or password");
        } catch (DisabledException e) {
            throw new UnauthorizedException("Account is locked");
        }

        handleSuccessfulLogin(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        log.info("{}:{} signed in Successfully", user.getRole(), user.getUsername());
        return new AuthenticationResponse(user.getUserId(), user.getFirstName(), accessToken, refreshToken, user.getRole(), "Successfully logged in");
    }

    @Transactional
    public AuthenticationResponse refreshToken(HttpServletRequest request, TokenRequest tokenRequest) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        validateAuthHeader(authHeader);

        String token = authHeader.substring(7);
        String accessEmail = jwtService.extractEmail(token);
        String refreshEmail = jwtService.extractEmail(tokenRequest.getToken());

        validateEmails(accessEmail, refreshEmail);

        User user = userRepository.findByEmail(refreshEmail).orElseThrow(() -> new NotFoundException("User not found"));

        if (jwtService.isTokenExpired(token)) {
            log.info("Access token is still valid for user: {}", refreshEmail);
            return new AuthenticationResponse(token, tokenRequest.getToken(), "Access Token is still valid");
        }

        if (jwtService.isValidRefreshToken(tokenRequest.getToken(), user)) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            log.info("{}:{} refreshed successfully", user.getRole(), user.getUsername());
            return new AuthenticationResponse(accessToken, refreshToken, "Token Refreshed Successfully");
        }
        throw new UnauthorizedException("Invalid Jwt Token");
    }

    private User getCurrentUser() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            if (email != null) {
                return userRepository.findByEmail(email).orElseGet(User::new);
            }
        } catch (Exception e) {
            log.error("Error fetching current user: {}", e.getMessage());
        }
        return new User();
    }

    private void encodePassword(User user) {
        try {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                String password = generatePassword(6);
                user.setPassword(passwordEncoder.encode(password));
                emailService.sendNewAccountEmail(user.getFirstName(), user.getEmail(), password);
            }
        } catch (Exception e) {
            log.error("Error encoding password or sending email: {}", e.getMessage());
            throw new ApiException("An error occurred during password encoding or email sending");
        }
    }

    private void initializeUserFields(User user, User currentUser) {
        user.setLoginAttempts(0);
        if (currentUser.getUserId() != 0) {
            user.setCreatedBy(currentUser);
            user.setUpdatedBy(currentUser);
        }
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
    }

    private Role determineUserRole(User currentUser, User user) {
        Role newRole = Role.USER; // Default to USER
        if (currentUser.getUserId() != 0 && user.getRole() != null) {
            switch (currentUser.getRole()) {
                case ADMIN:
                    newRole = user.getRole();
                    break;
                case DOCTOR:
                    if (user.getRole() == Role.MIDWIFE) {
                        newRole = Role.MIDWIFE;
                    }
                    break;
                case MIDWIFE:
                    if (user.getRole() == Role.PARENT || user.getRole() == Role.ELIGIBLE ||
                            user.getRole() == Role.PENDING || user.getRole() == Role.USER) {
                        newRole = user.getRole();
                    }
                    break;
                default:
                    log.warn("Unknown role for current user: {}", currentUser.getRole());
                    break;
            }
        }
        return newRole;
    }

    private void checkAccountLock(User user) {
        if (user.getLoginAttempts() != null && user.getLoginAttempts() >= 5) {
            throw new UnauthorizedException("Account is locked due to too many failed login attempts");
        }
    }

    private void authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    private void handleFailedLoginAttempt(User user) {
        if (user.getLoginAttempts() == null) {
            user.setLoginAttempts(1);
        } else {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
        }

        if (user.getLoginAttempts() >= 5) {
            user.setAccountNonLocked(false);
        }

        userRepository.save(user);
        log.warn("User {}: failed login attempt, login attempts: {}", user.getUsername(), user.getLoginAttempts());
    }

    private void handleSuccessfulLogin(User user) {
        user.setLoginAttempts(0);
        user.setAccountNonLocked(true);
        user.setLastLogin(now());
        userRepository.save(user);
        log.info("User {}: login attempts reset", user.getUsername());
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokenListByUser = tokenRepository.findAllAccessTokensByUser(user.getUserId());
        if (!validTokenListByUser.isEmpty()) {
            validTokenListByUser.forEach(token -> token.setLoggedOut(true));
            tokenRepository.saveAll(validTokenListByUser);
            log.info("Revoked all tokens for user {}", user.getUsername());
        }
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
        log.info("Saved new tokens for user {}", user.getUsername());
    }

    private void validateAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Invalid JWT token in the header");
            throw new UnauthorizedException("Invalid Jwt Token");
        }
    }

    private void validateEmails(String accessEmail, String refreshEmail) {
        if (!accessEmail.equals(refreshEmail)) {
            log.warn("Access token email and refresh token email do not match");
            throw new UnauthorizedException("Invalid access/refresh token");
        }
    }
}
