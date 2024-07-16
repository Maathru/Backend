package com.maathru.backend.Application.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DrugResponse {
    private long drugId;
    private String strength;
    private String brandName;
    private Integer quantity;
    private String batchNumber;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;
}
