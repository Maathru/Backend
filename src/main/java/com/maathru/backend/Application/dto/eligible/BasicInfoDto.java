package com.maathru.backend.Application.dto.eligible;

import com.maathru.backend.Domain.validation.PastDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BasicInfoDto {
    @NotNull(message = "Man's age cannot be empty")
    @Max(value = 100, message = "Man's age cannot be grater than 100")
    private int manAge;

    @NotNull(message = "Woman's age cannot be empty")
    @Max(value = 100, message = "Woman's age cannot be grater than 100")
    private int womanAge;

    @NotEmpty(message = "Man's education level cannot be empty")
    private String manEducationLevel;

    @NotEmpty(message = "Woman's education level cannot be empty")
    private String womanEducationLevel;

    @NotEmpty(message = "Man's occupation cannot be empty")
    private String manOccupation;

    @NotEmpty(message = "Woman's occupation cannot be empty")
    private String womanOccupation;

    @NotNull(message = "Married date cannot be empty")
    @PastDate(message = "Married date must be in the past")
    private LocalDate marriedDate;

    private String womanName;
    private String manName;
    private String address;
    private String location;
    private long userId;
    private LocalDate createdDate;
    private String region;
    private Object moh;
}
