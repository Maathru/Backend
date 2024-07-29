package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.eligible.EligibleCoupleDTO;
import com.maathru.backend.Application.dto.eligible.EligibleDto;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.service.EligibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eligible")
@RequiredArgsConstructor
@Validated
public class EligibleController {
    private final EligibleService eligibleService;

    @PreAuthorize("hasRole('MIDWIFE')")
    @PostMapping("/midwife/add")
    public ResponseEntity<String> createEligibleCouple(@Valid @RequestBody EligibleCoupleDTO eligibleCoupleDTO) {
        return eligibleService.createOrUpdateEligibleCouple(eligibleCoupleDTO);
    }

    @PreAuthorize("hasAnyRole('PARENT','ELIGIBLE')")
    @PostMapping
    public ResponseEntity<String> saveEligible(@Valid @RequestBody EligibleDto eligibleDto) {
        return eligibleService.saveOrUpdateEligible(eligibleDto);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/get/{userId}")
    public ResponseEntity<EligibleCoupleDTO> getEligibleDataForMidwife(@PathVariable long userId) {
        return eligibleService.getEligibleForMidwife(userId);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/get")
    public ResponseEntity<List<EligibleCoupleResponse>> getEligibleListForMidwife() {
        return eligibleService.getEligibleListForMidwife();
    }

    @PreAuthorize("hasAnyRole('PARENT','ELIGIBLE')")
    @GetMapping
    public ResponseEntity<EligibleDto> getEligibleData() {
        return eligibleService.getEligibleData();
    }
}
