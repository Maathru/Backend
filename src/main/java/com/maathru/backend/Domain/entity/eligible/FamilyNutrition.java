package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "family_nutrition")
public class FamilyNutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long familyNutritionId;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Gender gender;

    private boolean mainMeals;
    private String mainMealsDetails;

    private boolean animalProtein;
    private String animalProteinDetails;

    private boolean plantProtein;
    private String plantProteinDetails;

    private boolean twoTypesVegetables;
    private String twoTypesVegetablesDetails;

    private boolean oneTypeFruit;
    private String oneTypeFruitDetails;

    private boolean sitDownFamilyMeal;
    private String sitDownFamilyMealDetails;

    private boolean dietFromGarden;
    private String dietFromGardenDetails;

    private boolean tooMuchSugar;
    private String tooMuchSugarDetails;

    private boolean eatingTooMuchFat;
    private String eatingTooMuchFatDetails;
}
