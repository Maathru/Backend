package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.parent.*;
import com.maathru.backend.Application.dto.request.ChildMemoryDto;
import com.maathru.backend.Domain.service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parent")
@RequiredArgsConstructor
@Validated
public class ParentController {
    private final ParentService parentService;

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/main/parent")
    public ResponseEntity<String> createOrUpdateParentDetails(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid ParentDetailsDto parentDetailsDto) {
        return parentService.createOrUpdateParentDetails(userId, parentDetailsDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/main/parent")
    public ResponseEntity<ParentDetailsDto> getParentDetails(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getParentDetails(userId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/main/family")
    public ResponseEntity<String> createOrUpdateFamilyHistory(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid FamilyHistoryDto familyHistoryDto) {
        return parentService.createOrUpdateFamilyHistory(userId, familyHistoryDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/main/family")
    public ResponseEntity<FamilyHistoryDto> getFamilyHistory(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getFamilyHistory(userId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/main/pregnancy")
    public ResponseEntity<String> createOrUpdatePregnancyHistory(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid PregnancyHistoryDto pregnancyHistoryDto) {
        return parentService.createOrUpdatePregnancyHistory(userId, pregnancyHistoryDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/main/pregnancy")
    public ResponseEntity<PregnancyHistoryDto> getPregnancyHistory(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getPregnancyHistory(userId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/main/current")
    public ResponseEntity<String> createOrUpdateCurrentPregnancy(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid CurrentPregnancyDto currentPregnancyDto) {
        return parentService.createOrUpdateCurrentPregnancy(userId, currentPregnancyDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/main/current")
    public ResponseEntity<CurrentPregnancyDto> getCurrentPregnancy(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getCurrentPregnancy(userId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/clinical")
    public ResponseEntity<String> createClinicalConservation(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid ClinicalConservationDto clinicalConservationDto) {
        return parentService.createClinicalConservation(userId, clinicalConservationDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/clinical")
    public ResponseEntity<List<ClinicalConservationDto>> getClinicalConservations(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getClinicalConservations(userId);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @PostMapping("/pregnancy/child")
    public ResponseEntity<String> createOrUpdateChildBirth(@RequestParam(value = "user", required = false) Long userId, @RequestBody @Valid ChildBirthDto childBirthDto) {
        return parentService.createOrUpdateChildBirth(userId, childBirthDto);
    }

    @PreAuthorize("hasAnyRole('MIDWIFE','PARENT')")
    @GetMapping("/pregnancy/child")
    public ResponseEntity<ChildBirthDto> getChildBirth(@RequestParam(value = "user", required = false) Long userId) {
        return parentService.getChildBirth(userId);
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/memory/child/")
    public ResponseEntity<String> addMemoryChild(@RequestBody ChildMemoryDto childMemoryDto) {
        return parentService.addMemoryChild(childMemoryDto);
    }
}
