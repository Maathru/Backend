package com.maathru.backend.Application.dto.eligible;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FamilyPlanningMethodDTO {
    private LocalDate date;
    private String method;
}
