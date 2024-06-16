package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.Region;
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
    private Region region;
    private Employee doctor;
}