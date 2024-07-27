package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class EligibleDto {
    @Valid
    @NotNull(message = "Basic info object cannot be empty")
    private BasicInfoDto basicInfoDto;
    @Valid
    @NotNull(message = "Medical history object cannot be empty")
    private MedicalHistoryDto medicalHistoryDto;
    @Valid
    @NotNull(message = "Special woman object cannot be empty")
    private SpecialWomanDto specialWomanDto;
    @Valid
    @NotNull(message = "Special both object cannot be empty")
    private SpecialBothDto specialBothDto;
    @Valid
    @NotNull(message = "Family health info object cannot be empty")
    private FamilyHealthInfoDto familyHealthInfoDto;
    @Valid
    @NotNull(message = "Family nutrition object cannot be empty")
    private FamilyNutritionDto familyNutritionDto;
    @Valid
    @NotNull(message = "Parent habit object cannot be empty")
    private ParentHabitDto parentHabitDto;
    @Valid
    @NotNull(message = "Home environment object cannot be empty")
    private HomeEnvironmentDto homeEnvironmentDto;

    private MidwifeAssessmentDto midwifeAssessmentDto;
}
