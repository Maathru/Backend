package com.maathru.backend.Application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrugResponse {
    private long drugId;
    private String composition;
    private String strength;
    private String brandName;
    private Integer quantity;
    private String batchNumber;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;
}
