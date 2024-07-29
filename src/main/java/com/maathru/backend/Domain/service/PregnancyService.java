package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.PregnancyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class PregnancyService {
    public ResponseEntity<String> addPregnancy(PregnancyDto pregnancyDto) {
        return ResponseEntity.ok("Pregnancy added successfully");
    }
}
