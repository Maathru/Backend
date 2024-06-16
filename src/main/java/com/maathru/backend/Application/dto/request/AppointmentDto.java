package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AppointmentDto {
    private LocalTime assignedTime;
    private LocalTime completedTime;
    private String completedStatus;
    private Long updatedByEmployeeId;
    private Long doctorEmployeeId;
    private Long midwifeEmployeeId;
    private Long clinicClinicId;
    private Long adminEmployeeId;
    private Long parentParentId;
}
