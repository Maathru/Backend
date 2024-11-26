package com.maathru.backend.Application.dto.request;

import lombok.Data;

@Data
public class MedicalRecordDto {
    private long patientId;
    private String disease;
    private String medicationGiven;
    private String remarks;
    private String referredTo;
}