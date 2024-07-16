package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.BasicInfoDto;
import com.maathru.backend.Application.dto.request.eligible.MedicalHistoryDto;
import com.maathru.backend.Application.dto.request.eligible.FamilyHealthInfoDto;
import com.maathru.backend.Domain.service.EligibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/eligible")
@RequiredArgsConstructor
public class EligibleController {
    private final EligibleService eligibleService;

    @PostMapping("/basic")
    public ResponseEntity<String> saveBasicInfo(@Valid @RequestBody BasicInfoDto basicInfoDto) {
        return eligibleService.saveBasicInfo(basicInfoDto);
    }

    @PostMapping("/medical")
    public ResponseEntity<String> saveMedicalHistory(@Valid @RequestBody MedicalHistoryDto medicalHistoryDto) {
        return eligibleService.saveMedicalHistory(medicalHistoryDto);
    }

    @PostMapping("/family")
    public ResponseEntity<String> saveFamilyHealthInfo(@Valid @RequestBody FamilyHealthInfoDto familyHealthInfoDto) {
        return eligibleService.saveFamilyHealthInfo(familyHealthInfoDto);
    }
}
