package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MidwifeDashboard {
    private long eligibles;
    private long parents;
    private long clinics;
}
