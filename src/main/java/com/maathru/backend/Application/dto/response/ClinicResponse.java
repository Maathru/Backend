package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClinicResponse {
    private long clinicId;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long region;
    private String other;
    private List<DoctorsResponse> doctors;

    public ClinicResponse(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ClinicResponse(long clinicId, String name, LocalDate date, LocalTime startTime, LocalTime endTime, String region) {
        this.clinicId = clinicId;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.other = region;
    }
}
