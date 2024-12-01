package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.eligible.EligibleCoupleDTO;
import com.maathru.backend.Application.dto.eligible.EligibleDto;
import com.maathru.backend.Application.dto.response.EligibleCoupleResponse;
import com.maathru.backend.Domain.service.EligibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eligible")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EligibleController {
    private final EligibleService eligibleService;

    @PreAuthorize("hasRole('MIDWIFE')")
    @PostMapping("/midwife/add")
    public ResponseEntity<String> createEligibleCouple(@Valid @RequestBody EligibleCoupleDTO eligibleCoupleDTO) {
        return eligibleService.createOrUpdateEligibleCouple(eligibleCoupleDTO);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT','ELIGIBLE')")
    @PostMapping
    public ResponseEntity<String> saveEligible(@RequestParam(value = "user", required = false) Long userId,@Valid @RequestBody EligibleDto eligibleDto) {
        return eligibleService.saveOrUpdateEligible(userId,eligibleDto);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/get/{userId}")
    public ResponseEntity<EligibleCoupleDTO> getEligibleDataForMidwife(@PathVariable long userId) {
        return eligibleService.getEligibleForMidwife(userId);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @DeleteMapping("/midwife/delete/{userId}")
    public ResponseEntity<String> deleteEligibleUserByMidwife(@PathVariable long userId) {
        return eligibleService.deleteEligibleCouple(userId);
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/get")
    public ResponseEntity<List<EligibleCoupleResponse>> getEligibleListForMidwife() {
        return eligibleService.getEligibleListForMidwife();
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/parent/get")
    public ResponseEntity<List<EligibleCoupleResponse>> getParentListForMidwife() {
        return eligibleService.getParentListForMidwife();
    }

    @PreAuthorize("hasRole('MIDWIFE')")
    @GetMapping("/midwife/update/{userId}/{eligibleId}")
    public ResponseEntity<String> updateEligibleToParent(@PathVariable long userId, @PathVariable long eligibleId) {
        return eligibleService.updateEligibleToParent(userId, eligibleId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT','ELIGIBLE')")
    @GetMapping
    public ResponseEntity<EligibleDto> getEligibleData(@RequestParam(value = "user", required = false) Long userId) {
        return eligibleService.getEligibleData(userId);
    }
}
