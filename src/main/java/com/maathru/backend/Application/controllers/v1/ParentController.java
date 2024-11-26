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

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/main/parent")
    public ResponseEntity<String> createOrUpdateParentDetails(@RequestBody @Valid ParentDetailsDto parentDetailsDto) {
        return parentService.createOrUpdateParentDetails(parentDetailsDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/main/parent")
    public ResponseEntity<ParentDetailsDto> getParentDetails() {
        return parentService.getParentDetails();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/main/family")
    public ResponseEntity<String> createOrUpdateFamilyHistory(@RequestBody @Valid FamilyHistoryDto familyHistoryDto) {
        return parentService.createOrUpdateFamilyHistory(familyHistoryDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/main/family")
    public ResponseEntity<FamilyHistoryDto> getFamilyHistory() {
        return parentService.getFamilyHistory();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/main/pregnancy")
    public ResponseEntity<String> createOrUpdatePregnancyHistory(@RequestBody @Valid PregnancyHistoryDto pregnancyHistoryDto) {
        return parentService.createOrUpdatePregnancyHistory(pregnancyHistoryDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/main/pregnancy")
    public ResponseEntity<PregnancyHistoryDto> getPregnancyHistory() {
        return parentService.getPregnancyHistory();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/main/current")
    public ResponseEntity<String> createOrUpdateCurrentPregnancy(@RequestBody @Valid CurrentPregnancyDto currentPregnancyDto) {
        return parentService.createOrUpdateCurrentPregnancy(currentPregnancyDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/main/current")
    public ResponseEntity<CurrentPregnancyDto> getCurrentPregnancy() {
        return parentService.getCurrentPregnancy();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/clinical")
    public ResponseEntity<String> createClinicalConservation(@RequestBody @Valid ClinicalConservationDto clinicalConservationDto) {
        return parentService.createClinicalConservation(clinicalConservationDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/clinical")
    public ResponseEntity<List<ClinicalConservationDto>> getClinicalConservations() {
        return parentService.getClinicalConservations();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/pregnancy/child")
    public ResponseEntity<String> createOrUpdateChildBirth(@RequestBody @Valid ChildBirthDto childBirthDto) {
        return parentService.createOrUpdateChildBirth(childBirthDto);
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/pregnancy/child")
    public ResponseEntity<ChildBirthDto> getChildBirth() {
        return parentService.getChildBirth();
    }

    @PreAuthorize("hasRole('PARENT')")
    @PostMapping("/memory/child/{userId}")
    public ResponseEntity<ChildMemoryDto> addMemoryChild(@PathVariable Long userId, @RequestBody  ChildMemoryDto childMemoryDto) {
        return parentService.addMemoryChild(userId,childMemoryDto);
    }
}
