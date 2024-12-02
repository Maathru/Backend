package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.UpdateEmployeeProfileDto;
import com.maathru.backend.Application.dto.request.UpdateUserProfileDto;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            if (users.isEmpty()) {
                throw new NotFoundException("No users found");
            }

            List<UserProfileDto> userProfileDtos = users.stream().map(user -> {
                UserProfileDto userProfileDto = new UserProfileDto();

                userProfileDto.setId(user.getUserId());
                userProfileDto.setFirstName(user.getFirstName());
                userProfileDto.setLastName(user.getLastName());
                userProfileDto.setEmail(user.getEmail());
                userProfileDto.setRole(user.getRole());
                userProfileDto.setLastLogin(user.getLastLogin());
                return userProfileDto;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(userProfileDtos);
        } catch (Exception e) {
            log.error("Error retrieving all users {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<UserProfileDto> getUser() {
        try {
            User currentUser = jwtService.getCurrentUser();

            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setId(currentUser.getUserId());
            userProfileDto.setFirstName(currentUser.getFirstName());
            userProfileDto.setLastName(currentUser.getLastName());
            userProfileDto.setEmail(currentUser.getEmail());
            userProfileDto.setRole(currentUser.getRole());
            userProfileDto.setLastLogin(currentUser.getLastLogin());

            if (currentUser.getRole() == Role.USER || currentUser.getRole() == Role.ELIGIBLE || currentUser.getRole() == Role.PARENT || currentUser.getRole() == Role.PENDING) {
                return ResponseEntity.ok(userProfileDto);
            }

            Employee employee = employeeRepository.findByUser(currentUser).orElseThrow(() -> new NotFoundException("Employee not found"));
            userProfileDto.setPhoneNumber(employee.getPhoneNumber());
            userProfileDto.setNic(employee.getNic());
            userProfileDto.setGender(employee.getGender());
            userProfileDto.setDob(employee.getDob());
            userProfileDto.setAddressLine1(employee.getAddressLine1());
            userProfileDto.setStreet(employee.getStreet());
            userProfileDto.setCity(employee.getCity());
            userProfileDto.setDesignation(employee.getDesignation());
            userProfileDto.setQualifications(employee.getQualifications());
            userProfileDto.setMoh(String.valueOf(employee.getMoh().getArea()));
            userProfileDto.setRegion(employee.getRegion().getRegionName());

            return ResponseEntity.ok(userProfileDto);
        } catch (Exception e) {
            log.error("Error retrieving user {}", e.getMessage());
            if (e instanceof NotFoundException) throw e;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UnauthorizedException("Invalid username"));
    }

    // For midwife
    public ResponseEntity<Long> getUserIdByEmailForMidwife(String email) {
        if (email == null || email.isEmpty()) {
            throw new NotFoundException("Email is null or empty");
        }

        long userId = userRepository.findUserIdByEmailAndRole(email, Role.USER).orElseThrow(() -> new NotFoundException("User not found"));
        return ResponseEntity.ok(userId);
    }

    @Transactional
    public ResponseEntity<String> updateEmployeeProfile(UpdateEmployeeProfileDto updateEmployeeProfileDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            currentUser.setFirstName(updateEmployeeProfileDto.getFirstName());
            currentUser.setLastName(updateEmployeeProfileDto.getLastName());
            currentUser.setUpdatedBy(currentUser);

            userRepository.save(currentUser);
            log.info("User profile updated successfully: {} by {}: {}", currentUser.getUsername(), currentUser.getRole(), currentUser.getUserId());

            if (currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.DOCTOR || currentUser.getRole() == Role.MIDWIFE) {
                Employee employee = employeeRepository.findByUserEmail(currentUser.getEmail()).orElseThrow(() -> new NotFoundException("Employee not found"));

                employee.setPhoneNumber(updateEmployeeProfileDto.getPhoneNumber());
                employee.setNic(updateEmployeeProfileDto.getNic());
                employee.setAddressLine1(updateEmployeeProfileDto.getAddressLine1());
                employee.setStreet(updateEmployeeProfileDto.getStreet());
                employee.setCity(updateEmployeeProfileDto.getCity());
                employee.setDesignation(updateEmployeeProfileDto.getDesignation());
                employee.setQualifications(updateEmployeeProfileDto.getQualifications());
                employee.setUpdatedBy(currentUser);

                employeeRepository.save(employee);
                log.info("Employee profile updated successfully: {} by {}: {}", currentUser.getUsername(), currentUser.getRole(), currentUser.getUserId());
            }

            return ResponseEntity.ok("User profile updated successfully");
        } catch (Exception e) {
            log.error("Error during update profile: {}", e.getMessage());
            if (e instanceof NotFoundException) throw e;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during update profile");
        }
    }

    @Transactional
    public ResponseEntity<String> updateUserProfile(UpdateUserProfileDto updateUserProfileDto) {
        try {
            User currentUser = jwtService.getCurrentUser();
            currentUser.setFirstName(updateUserProfileDto.getFirstName());
            currentUser.setLastName(updateUserProfileDto.getLastName());
            currentUser.setUpdatedBy(currentUser);

            userRepository.save(currentUser);
            log.info("User profile updated successfully: {} by {}: {}", currentUser.getUsername(), currentUser.getRole(), currentUser.getUserId());
            return ResponseEntity.ok("User profile updated successfully");
        } catch (Exception e) {
            log.error("Error during update profile: {}", e.getMessage());
            if (e instanceof NotFoundException) throw e;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during update profile");
        }
    }
}
