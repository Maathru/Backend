package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Domain.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

    @RequestMapping("/pregnancy-count-by-region")
    public List<Map<String, Object>> getPregnancyCountByRegion() {
        return analyticsService.getPregnancyCountByRegion();
    }

}
