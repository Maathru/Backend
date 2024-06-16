package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.Parent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private LocalDateTime assignedTime;
    private Employee doctor;
    private Clinic clinic;
    private Parent parent;
}
