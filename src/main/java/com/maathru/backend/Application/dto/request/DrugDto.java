package com.maathru.backend.Application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DrugDto {
    @NotEmpty(message = "Composition cannot be empty")
    private String composition;

    @NotEmpty(message = "Strength cannot be empty")
    private String strength;

    @NotEmpty(message = "Brand Name cannot be empty")
    private String brandName;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 0,message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotEmpty(message = "Batch number cannot be empty")
    private String batchNumber;

    private String recommendedDose;

    @NotEmpty(message = "Expiry date cannot be empty")
    private LocalDate expiryDate;

    @NotEmpty(message = "Manufactured date cannot be empty")
    private LocalDate manufacturedDate;
}