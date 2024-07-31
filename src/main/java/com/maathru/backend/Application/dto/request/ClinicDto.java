package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Application.dto.response.DoctorsResponse;
import com.maathru.backend.Domain.validation.FutureDate;
import com.maathru.backend.Domain.validation.ValidClinicTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ValidClinicTime
public class ClinicDto {
    @NotEmpty(message = "Clinic name is required")
    private String name;

    @NotNull(message = "Region is required")
    private long region;

    @FutureDate(message = "Date must be future")
    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    private String other;

    @Valid
    @NotNull(message = "Doctors are required")
    private List<DoctorsResponse> doctors;
}