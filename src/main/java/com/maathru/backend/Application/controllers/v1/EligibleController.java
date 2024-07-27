package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.eligible.BasicInfoDto;
import com.maathru.backend.Application.dto.eligible.EligibleCoupleDTO;
import com.maathru.backend.Application.dto.eligible.EligibleDto;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.service.EligibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eligible")
@RequiredArgsConstructor
@Validated
public class EligibleController {
    private final EligibleService eligibleService;

    @PostMapping("/midwife/add")
    public ResponseEntity<String> createEligibleCouple(@Valid @RequestBody EligibleCoupleDTO eligibleCoupleDTO) {
        return eligibleService.createOrUpdateEligibleCouple(eligibleCoupleDTO);
    }

    @PostMapping
    public ResponseEntity<String> saveEligible(@Valid @RequestBody EligibleDto eligibleDto) {
        return eligibleService.saveOrUpdateEligible(eligibleDto);
    }

    @GetMapping("/midwife/get/{userId}")
    public ResponseEntity<EligibleCoupleDTO> getEligibleDataForMidwife(@PathVariable long userId) {
        return eligibleService.getEligibleForMidwife(userId);
    }

    @GetMapping("/midwife/get")
    public ResponseEntity<List<EligibleCoupleResponse>> getEligibleListForMidwife() {
        return eligibleService.getEligibleListForMidwife();
    }

    @GetMapping
    public ResponseEntity<EligibleDto> getEligibleData() {
        return eligibleService.getEligibleData();
    }
}
