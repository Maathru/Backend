package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EligibleDto {
    @NotNull(message = "Basic info object cannot be empty")
    private BasicInfoDto basicInfoDto;
    @NotNull(message = "Medical history object cannot be empty")
    private MedicalHistoryDto medicalHistoryDto;
    @NotNull(message = "Special woman object cannot be empty")
    private SpecialWomanDto specialWomanDto;
    @NotNull(message = "Special both object cannot be empty")
    private SpecialBothDto specialBothDto;
    @NotNull(message = "Family health info object cannot be empty")
    private FamilyHealthInfoDto familyHealthInfoDto;
    @NotNull(message = "Family nutrition object cannot be empty")
    private FamilyNutritionDto familyNutritionDto;
}
