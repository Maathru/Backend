package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ClinicDto {
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long region;
    private long doctor;
}