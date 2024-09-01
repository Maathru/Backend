package com.maathru.backend.Application.dto.eligible;

import com.maathru.backend.Domain.validation.PastDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EligibleCoupleDTO {
    @NotNull(message = "User id cannot be empty")
    private long userId;

    @NotEmpty(message = "Woman's name cannot be empty")
    private String womanName;
    private String manName;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "Woman's telephone number cannot be empty")
    private String womanPhone;
    private String manPhone;

    @NotNull(message = "Woman's date of birth cannot be empty")
    @PastDate(message = "Woman's date of birth must be in the past")
    private LocalDate womanDob;
    private LocalDate manDob;

    private int womanAgeMarried;
    private int manAgeMarried;

    @NotEmpty(message = "Woman's education level cannot be empty")
    private String womanEducationLevel;
    private String manEducationLevel;

    @NotEmpty(message = "Woman's occupation cannot be empty")
    private String womanOccupation;
    private String manOccupation;

    @NotNull(message = "Number of children alive cannot be empty")
    private int children;

    @NotNull(message = "Woman's weight cannot be empty")
    private double womanWeight; // in kg
    @NotNull(message = "Woman's height cannot be empty")
    private double womanHeight; // in meters
    @NotNull(message = "Woman's bmi cannot be empty")
    private double womanBmi; // Body mass index (BMI) kg/m^2
    @NotEmpty(message = "Woman's blood type cannot be empty")
    private String womanBloodType;
    @NotNull(message = "Woman's hemoglobin level cannot be empty")
    private double womanHemoglobin;

    // Men's details
    private double manWeight; // in kg
    private double manHeight; // in meters
    private double manBmi; // Body mass index (BMI) kg/m^2
    private String manBloodType;
    private double manHemoglobin;

    private String special;
    private String session;

    private List<PastPregnancyDTO> pastPregnancies;
    private List<FamilyPlanningMethodDTO> familyPlanningMethods;
}
