package com.maathru.backend.Application.dto.parent;

import com.maathru.backend.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChildBirthDto {
    private String birthPlace;
    private LocalDate dob;
    private String dateReleased;
    private String doneBy;
    private String resultOfPregnancy;
    private String statusOfPregnancy;

    private boolean crackedAround;
    private boolean postpartumBleeding;
    private boolean trappedAura;
    private boolean anyCutsAround;
    private boolean longDelivery;
    private boolean otherComplications;
    private boolean vitaminADose;
    private boolean rubellaVaccine;

    private Gender sex;
    private float birthWeight;
    private boolean prematureBirth;
    private boolean complicationsAtBirth;

    private boolean motherDead;
    private LocalDate motherDeadDate;
    private String motherDeadCause;
    private boolean investigated;
}
