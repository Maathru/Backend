package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.response.PregnancyCardForGrowthResponse;
import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.ChildBirthRepository;
import com.maathru.backend.External.repository.ParentRepository;
import com.maathru.backend.External.repository.PregnancyCardRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GrowthService {
    private static final Logger log = LoggerFactory.getLogger(GrowthService.class);
    private final ChildBirthRepository childBirthRepository;
    private final PregnancyCardRepository pregnancyCardRepository;
    private final ParentRepository parentRepository;
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

    public ResponseEntity<LocalDate> getDop() {
        try {
            User currentUser = jwtService.getCurrentUser();
            Parent parent = parentRepository.findByUser(currentUser);
            LocalDate dop = pregnancyCardRepository.findByParent(parent).get().getDateOfPregnancy();
            return ResponseEntity.status(HttpStatus.OK).body(dop);
        } catch (Exception e){
            log.error("Error retrieving pregnancy date for user: {} {}", jwtService.getCurrentUser().getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}