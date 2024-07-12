package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private LocalDateTime assignedTime;
    private long doctor;
    private long clinic;
    private long parent;
}
