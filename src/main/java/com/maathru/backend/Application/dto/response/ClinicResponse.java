package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ClinicResponse {
    private long clinicId;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long region;
    private String other;
    private List<DoctorsResponse> doctors;
}
