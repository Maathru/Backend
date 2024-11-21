package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicListResponse {
    private long id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String region;

    public ClinicListResponse(long id, String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ClinicListResponse(long clinicId, String name, String region) {
        this.id = clinicId;
        this.name = name;
        this.region = region;
    }
}
