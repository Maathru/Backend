package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.response.AdminDashboard;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.External.repository.BlogRepository;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.RegionRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.maathru.backend.constant.Constant.PENDING_BLOG;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BlogRepository blogRepository;
    private final RegionRepository regionRepository;
    private final ClinicRepository clinicRepository;

    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            if (users.isEmpty()) {
                throw new NotFoundException("No users found");
            }

            List<UserProfileDto> userProfileDtos = users.stream().map(user -> {
                UserProfileDto userProfileDto = new UserProfileDto();

                userProfileDto.setId(user.getUserId());
                userProfileDto.setName(user.getFirstName() + " " + user.getLastName());
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

    public ResponseEntity<UserProfileDto> getUser(long id) {
        UserProfileDto userProfileDto = new UserProfileDto();
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userProfileDto.setId(user.getUserId());
            userProfileDto.setName(user.getFirstName() + " " + user.getLastName());
            userProfileDto.setEmail(user.getEmail());
            userProfileDto.setRole(user.getRole());
            userProfileDto.setLastLogin(user.getLastLogin());

            return ResponseEntity.ok(userProfileDto);
        } else {
            log.error("user not found");
            throw new NotFoundException("User not found");
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

    // For Admin Dashboard
    public ResponseEntity<AdminDashboard> getAdminDashboardData(){
        User user = jwtService.getCurrentUser();

        log.info("1");

        AdminDashboard adminDashboard = new AdminDashboard();
        log.info("2");
        adminDashboard.setUsers(userRepository.countByEnabled(true));
        log.info("3");
        adminDashboard.setBlogsToConfirm(blogRepository.countByApprovalStatus(PENDING_BLOG));
        log.info("4");
        adminDashboard.setThisMonthClinics(clinicRepository.countClinicsInCurrentMonth());
        log.info("5");
        adminDashboard.setRegions(regionRepository.countByEmployeeAndMOH(user.getEmail()));
        log.info("6");

        return ResponseEntity.ok(adminDashboard);
    }
}
