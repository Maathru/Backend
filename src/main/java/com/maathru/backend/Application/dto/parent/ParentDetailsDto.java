package com.maathru.backend.Application.dto.parent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ParentDetailsDto {
    private String womanName;
    private String manName;

    private String address;
    private String location;

    private String womanPhone;
    private String manPhone;

    private LocalDate womanDob;
    private LocalDate manDob;

    private int womanAgeMarried;
    private int manAgeMarried;

    private String womanEducationLevel;
    private String manEducationLevel;

    private String womanOccupation;
    private String manOccupation;

    private String distance;

    private boolean bloodRelatives;
    private String bloodRelativesDetails;

    private boolean rubella;
    private String rubellaDetails;

    private boolean pregnancyScreening;
    private String pregnancyScreeningDetails;

    private boolean folicAcid;
    private String folicAcidDetails;

    private boolean infertility;
    private String infertilityDetails;
}
