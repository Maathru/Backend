package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DrugDto {
    private String composition;
    private String strength;
    private String brandName;
    private Integer quantity;
    private String batchNumber;
    private String recommendedDose;
    private LocalDate expiryDate;
    private LocalDate manufacturedDate;
    private LocalDate receivedDate;
}