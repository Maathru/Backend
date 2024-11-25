package com.maathru.backend.Domain.service;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.ChildBirthRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GrowthService {
    private static final Logger log = LoggerFactory.getLogger(GrowthService.class);
    private final ChildBirthRepository childBirthRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public ResponseEntity<LocalDate> getDob() {
        try {
            User currentUser = jwtService.getCurrentUser();
            LocalDate dob = childBirthRepository.findByUserAndDeletedAtIsNull(currentUser).get().getDob();
            return ResponseEntity.status(HttpStatus.OK).body(dob);
        } catch (Exception e){
            log.error("Error retrieving Child Birth date for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}