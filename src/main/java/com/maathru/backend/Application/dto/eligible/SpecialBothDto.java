package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialBothDto {
    @NotNull(message = "Man's Are you dissatisfied with sex? field value cannot be empty")
    private boolean manDissatisfiedSex;
    @NotNull(message = "Man's Do you both use a family planning system? field value cannot be empty")
    private boolean manFamilyPlaning;
    @NotNull(message = "Man's Looking to delay the birth of your first child? field value cannot be empty")
    private boolean manDelayFirstBirth;

    @NotNull(message = "Woman's Are you dissatisfied with sex? field value cannot be empty")
    private boolean womanDissatisfiedSex;
    @NotNull(message = "Woman's Do you both use a family planning system? field value cannot be empty")
    private boolean womanFamilyPlaning;
    @NotNull(message = "Woman's Looking to delay the birth of your first child? field value cannot be empty")
    private boolean womanDelayFirstBirth;
    @NotNull(message = "Woman's Do you do breast self-examination once a month? field value cannot be empty")
    private boolean manBreastExamination;

    private String dissatisfiedSexDetails;
    private String familyPlaningDetails;
    private String delayFirstBirthDetails;
    private String breastExaminationDetails;
}
