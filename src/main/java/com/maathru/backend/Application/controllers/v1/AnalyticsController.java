package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Domain.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Validated
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/pregnancy-count-by-region")
    public List<Map<String, Object>> getPregnancyCountByRegion() {
        return analyticsService.getPregnancyCountByRegion();
    }

    @GetMapping("/pregnancy-count-by-age")
    public List<Map<String, Object>> getPregnancyCountByAge() {
        return analyticsService.getPregnancyCountByAge();
    }

    @GetMapping("/births-by-month")
    public Map<Integer, Long> getBirthsCountByMonth() {
        return analyticsService.getBirthsCountByMonth();
    }

    @GetMapping("/birth-weights")
    public List<Float> getBirthWeights() {
        return analyticsService.getBirthWeights();
    }


    @GetMapping("/health-conditions-stats")
    public ResponseEntity<Map<String, Long>> getHealthConditionsStats() {
        return ResponseEntity.ok(analyticsService.getHealthConditionsStats());
    }

    @GetMapping("/bmi")
    public ResponseEntity<Map<String, Long>> analyzeBMIByCategory() {
        return ResponseEntity.ok(analyticsService.analyzeBMIByCategory());
    }

}
