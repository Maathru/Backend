package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ClinicDto {
    private String clinicName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long regionId;
    private Long doctorId;
    private Long adminId;

}
