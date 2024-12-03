package com.maathru.backend.Application.dto.request;

import com.maathru.backend.enumeration.VisitStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class VisitDto {
    private LocalDate date;
    private LocalTime time;
    private VisitStatus status;
}
