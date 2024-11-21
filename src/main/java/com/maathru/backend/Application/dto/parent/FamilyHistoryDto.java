package com.maathru.backend.Application.dto.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyHistoryDto {
    private boolean womanDiabetes;
    private boolean womanHighBloodPressure;
    private boolean womanBloodRelatedDiseases;
    private boolean other;

    private String diabetesDetails;
    private String highBloodPressureDetails;
    private String womanBloodRelatedDiseasesDetails;
    private String otherDetails;
}
