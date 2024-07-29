package com.maathru.backend.Application.dto.pregnancy;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyMedicalHistoryDto {
    @NotNull(message = "Pregnancy card should be selected")
    private long pregnancyCardId;
    private Boolean bloodRelatives;
    private Boolean rubella;
    private Boolean pregnancyScreening;
    private Boolean folicAcid;
    private Boolean infertility;
    private String bloodRelativesDetails;
    private String rubellaDetails;
    private String pregnancyScreeningDetails;
    private String folicAcidDetails;
    private String infertilityDetails;
}
