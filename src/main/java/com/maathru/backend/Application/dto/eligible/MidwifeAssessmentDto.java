package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MidwifeAssessmentDto {
    @NotEmpty(message = "Woman's weight cannot be empty")
    private double womanWeight; // in kg
    @NotEmpty(message = "Woman's height cannot be empty")
    private double womanHeight; // in meters
    @NotEmpty(message = "Woman's bmi cannot be empty")
    private double womanBmi; // Body mass index (BMI) kg/m^2
    @NotEmpty(message = "Woman's blood type cannot be empty")
    private String womanBloodType;
    @NotEmpty(message = "Woman's hemoglobin level cannot be empty")
    private double womanHemoglobin;

    // Men's details
    private double manWeight; // in kg
    private double manHeight; // in meters
    private double manBmi; // Body mass index (BMI) kg/m^2
    private String manBloodType;
    private double manHemoglobin;

    private String special;
    private String session;
}
