package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentHabitDto {
    @NotNull(message = "Man's Cigarettes, betel nuts, tobacco field value cannot be empty")
    private boolean manCigarettes;
    @NotNull(message = "Man's Alcohol field value cannot be empty")
    private boolean manAlcohol;
    @NotNull(message = "Man's Drugs field value cannot be empty")
    private boolean manDrugs;
    @NotNull(message = "Man's smoke, are you passively field value cannot be empty")
    private boolean manSmoke;
    @NotNull(message = "Man's atmosphere in the head that is not violent physically or mentally field value cannot be empty")
    private boolean manViolent;
    @NotNull(message = "Man's practice daily religious rituals field value cannot be empty")
    private boolean manRituals;
    @NotNull(message = "Man's making time for regular exercise field value cannot be empty")
    private boolean manExercise;
    @NotNull(message = "Man's make time for hobbies field value cannot be empty")
    private boolean manHobbies;

    @NotNull(message = "Woman's Cigarettes, betel nuts, tobacco field value cannot be empty")
    private boolean womanCigarettes;
    @NotNull(message = "Woman's Alcohol field value cannot be empty")
    private boolean womanAlcohol;
    @NotNull(message = "Woman's Drugs field value cannot be empty")
    private boolean womanDrugs;
    @NotNull(message = "Woman's smoke, are you passively field value cannot be empty")
    private boolean womanSmoke;
    @NotNull(message = "Woman's atmosphere in the head that is not violent physically or mentally field value cannot be empty")
    private boolean womanViolent;
    @NotNull(message = "Woman's practice daily religious rituals field value cannot be empty")
    private boolean womanRituals;
    @NotNull(message = "Woman's making time for regular exercise field value cannot be empty")
    private boolean womanExercise;
    @NotNull(message = "Woman's make time for hobbies field value cannot be empty")
    private boolean womanHobbies;

    private String cigarettesDetails;
    private String alcoholDetails;
    private String drugsDetails;
    private String smokeDetails;
    private String violentDetails;
    private String ritualsDetails;
    private String exerciseDetails;
    private String hobbiesDetails;
}
