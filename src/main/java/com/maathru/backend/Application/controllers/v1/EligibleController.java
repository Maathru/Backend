package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.eligible.EligibleDto;
import com.maathru.backend.Domain.service.EligibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/eligible")
@RequiredArgsConstructor
public class EligibleController {
    private final EligibleService eligibleService;

    @PostMapping
    public ResponseEntity<String> saveEligible(@Valid @RequestBody EligibleDto eligibleDto) {
        return eligibleService.saveEligible(eligibleDto);
    }

    @GetMapping
    public ResponseEntity<EligibleDto> getEligibleData() {
        return eligibleService.getEligibleData();
    }
}
