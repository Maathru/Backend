package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordResponse {
    private Long recordId;
    private Long patientId;
    private String patientName;
    private String disease;
    private String medicationGiven;
    private String remarks;
    private String referredTo;

}
