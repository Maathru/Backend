package com.maathru.backend.Application.dto.response;

import com.maathru.backend.Domain.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ClinicResponse {
    private long clinicId;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String other;
//    private long doctorId;
//    private String doctorName;
    private List<DoctorsResponse> doctors;
}
