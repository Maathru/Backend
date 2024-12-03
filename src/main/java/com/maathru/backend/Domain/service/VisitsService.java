package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.HomeVisitRequest;
import com.maathru.backend.Application.dto.request.VisitDto;
import com.maathru.backend.Application.dto.response.HomeVisitsListResponse;
import com.maathru.backend.Domain.entity.HomeVisit;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.Visit;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.HomeVisitRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitsService {
    private final HomeVisitRepository homeVisitRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public ResponseEntity<String> saveOrUpdateHomeVisits(HomeVisitRequest homeVisitRequest) {
        try {
            User currentUser = jwtService.getCurrentUser();
            User user = userRepository.findById(homeVisitRequest.getId())
                    .orElseThrow(() -> new NotFoundException("User not found"));

            HomeVisit homeVisit = homeVisitRepository.findByUser(user).orElseGet(HomeVisit::new);
            homeVisit.setUser(user);

            if (homeVisit.getId() == 0) {
                homeVisit.setCreatedBy(currentUser);
            }
            homeVisit.setUpdatedBy(currentUser);

            // Clear existing visits and add new ones
            homeVisit.getVisits().clear();
            List<Visit> visits = homeVisitRequest.getVisits().stream()
                    .map(visitDto -> {
                        Visit visit = new Visit();
                        visit.setDate(visitDto.getDate());
                        visit.setTime(visitDto.getTime());
                        visit.setStatus(visitDto.getStatus());
                        visit.setHomeVisit(homeVisit);
                        return visit;
                    })
                    .toList();
            homeVisit.getVisits().addAll(visits);

            homeVisitRepository.save(homeVisit);
            log.info("Home visits saved successfully for user: {}", user.getEmail());
            return ResponseEntity.ok("Home visits saved successfully!");
        } catch (NotFoundException e) {
            log.error("User not found: {}", homeVisitRequest.getId());
            throw e;
        } catch (Exception e) {
            log.error("Error saving home visits for user: {} - {}", jwtService.getCurrentUser().getEmail(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving home visits");
        }
    }


    @Transactional(readOnly = true)
    public ResponseEntity<HomeVisitRequest> getHomeVisits(long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

            HomeVisit homeVisit = homeVisitRepository.findByUser(user)
                    .orElseThrow(() -> new NotFoundException("Home visits not found for user id: " + userId));

            List<VisitDto> visits = homeVisit.getVisits().stream()
                    .map(visit -> {
                        VisitDto visitDto = new VisitDto();
                        visitDto.setDate(visit.getDate());
                        visitDto.setTime(visit.getTime());
                        visitDto.setStatus(visit.getStatus());
                        return visitDto;
                    })
                    .collect(Collectors.toList());

            HomeVisitRequest homeVisitRequest = new HomeVisitRequest();
            homeVisitRequest.setVisits(visits);
            homeVisitRequest.setId(userId);

            return ResponseEntity.ok(homeVisitRequest);
        } catch (NotFoundException e) {
            log.error("Error fetching home visits: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error fetching home visits for user id {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<List<HomeVisitsListResponse>> getHomeVisitsGivenMonthForMidwife(String date) {
        try {
            User currentUser = jwtService.getCurrentUser();

            LocalDate localDate = LocalDate.parse(date);
            List<HomeVisitsListResponse> homeVisitsListResponseList = homeVisitRepository.findHomeVisitsByMonthForMidwife(localDate, currentUser.getEmail());

            if (homeVisitsListResponseList.isEmpty()) {
                log.warn("Home visits not found for {}", localDate.getMonth());
                throw new NotFoundException("Home visits not found for " + localDate.getMonth());
            }
            return ResponseEntity.ok(homeVisitsListResponseList);
        } catch (Exception e) {
            if (e instanceof NotFoundException) throw e;
            log.error("Unexpected error fetching home visits");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<HomeVisitsListResponse>> getHomeVisitsGivenDateForMidwife(String date) {
        try {
            User currentUser = jwtService.getCurrentUser();

            LocalDate localDate = LocalDate.parse(date);
            List<HomeVisitsListResponse> homeVisitsListResponseList = homeVisitRepository.findHomeVisitsByDateForMidwife(localDate, currentUser.getEmail());

            if (homeVisitsListResponseList.isEmpty()) {
                log.warn("Home visits not found for {}", date);
                throw new NotFoundException("Home visits not found for " + date);
            }
            return ResponseEntity.ok(homeVisitsListResponseList);
        } catch (Exception e) {
            if (e instanceof NotFoundException) throw e;
            log.error("Unexpected error fetching home visits");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<LocalDate>> getHomeVisitDatesGivenDateForParent(String date) {
        try {
            User currentUser = jwtService.getCurrentUser();

            LocalDate localDate = LocalDate.parse(date);
            List<LocalDate> dates = homeVisitRepository.findHomeVisitDatesByDateForParent(localDate, currentUser.getEmail());

            if (dates.isEmpty()) {
                log.warn("Home visits not found for month {}", localDate.getMonth());
                throw new NotFoundException("Home visits not found for month " + localDate.getMonth());
            }
            return ResponseEntity.ok(dates);
        } catch (Exception e) {
            if (e instanceof NotFoundException) throw e;
            log.error("Unexpected error fetching home visits");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
