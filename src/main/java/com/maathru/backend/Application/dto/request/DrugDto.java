package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DrugDto {
    private String batchNumber;
    private String composition;
    private String strength;
    private String brandName;
    private Integer quantity;
    private LocalDate expiryDate;
    private LocalDate manufacturedDate;
    private LocalDate receivedDate;
    private LocalDate registeredDate;
    private String recommendedDose;

}
