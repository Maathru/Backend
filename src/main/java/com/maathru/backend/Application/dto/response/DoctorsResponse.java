package com.maathru.backend.Application.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class DoctorsResponse {
    @NotNull(message = "Doctor Id cannot be empty")
    private long id;
    @NotEmpty(message = "Doctor name is required")
    private String name;
}
