package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Application.dto.pregnancy.CurrentPregnancyStatusDto;
import com.maathru.backend.Application.dto.pregnancy.ExistingMedicalConditionsDto;
import com.maathru.backend.Application.dto.pregnancy.FamilyMedicalHistoryDto;
import com.maathru.backend.Application.dto.pregnancy.PresentObstetricHistoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PregnancyDto {
    private CurrentPregnancyStatusDto currentPregnancyStatusDto;
    private ExistingMedicalConditionsDto existingMedicalConditionsDto;
    private FamilyMedicalHistoryDto familyMedicalHistoryDto;
    private PresentObstetricHistoryDto presentObstetricHistoryDto;
}
