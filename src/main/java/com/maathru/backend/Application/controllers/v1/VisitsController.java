package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.HomeVisitRequest;
import com.maathru.backend.Application.dto.response.ClinicListResponse;
import com.maathru.backend.Application.dto.response.HomeVisitsListResponse;
import com.maathru.backend.Domain.service.VisitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@AllArgsConstructor
public class VisitsController {
    private final VisitsService visitsService;

    @PreAuthorize("hasRole('MIDWIFE')")
    @PostMapping
    public ResponseEntity<String> saveOrUpdateHomeVisits(@RequestBody HomeVisitRequest homeVisitRequest) {
        return visitsService.saveOrUpdateHomeVisits(homeVisitRequest);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/{userId}")
    public ResponseEntity<HomeVisitRequest> getHomeVisits(@PathVariable long userId) {
        return visitsService.getHomeVisits(userId);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/month/midwife/{date}")
    public ResponseEntity<List<HomeVisitsListResponse>> getHomeVisitsGivenMonthForMidwife(@PathVariable String date) {
        return visitsService.getHomeVisitsGivenMonthForMidwife(date);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/date/midwife/{date}")
    public ResponseEntity<List<HomeVisitsListResponse>> getHomeVisitsGivenDateForMidwife(@PathVariable String date) {
        return visitsService.getHomeVisitsGivenDateForMidwife(date);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/list/parent/{date}")
    public ResponseEntity<List<LocalDate>> getHomeVisitDatesGivenDateForParent(@PathVariable String date) {
        return visitsService.getHomeVisitDatesGivenDateForParent(date);
    }
}
