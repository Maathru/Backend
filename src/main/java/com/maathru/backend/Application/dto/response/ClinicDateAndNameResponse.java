package com.maathru.backend.Application.dto.response;

import java.time.LocalDate;

public record ClinicDateAndNameResponse(String name, LocalDate date) {
}
