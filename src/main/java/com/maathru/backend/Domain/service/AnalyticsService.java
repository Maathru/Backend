package com.maathru.backend.Domain.service;

import com.maathru.backend.External.repository.PregnancyCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AnalyticsService {
    private final PregnancyCardRepository pregnancyCardRepository;

    public List<Map<String, Object>> getPregnancyCountByRegion() {
        return pregnancyCardRepository.getPregnancyCountByRegion();
    }
}
