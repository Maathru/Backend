package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyNutritionDto {
    @NotNull(message = "Man's Did you have 3 main meals for the day? filed value cannot be empty")
    private boolean manThreeMeals;
    @NotNull(message = "Man's Animal protein filed value cannot be empty")
    private boolean manAnimalProtein;
    @NotNull(message = "Man's Plant protein/a grain filed value cannot be empty")
    private boolean manPlantProtein;
    @NotNull(message = "Man's Two types of vegetables - Daily filed value cannot be empty")
    private boolean manVegetable;
    @NotNull(message = "Man's One type of fruit - Daily filed value cannot be empty")
    private boolean manFruit;
    @NotNull(message = "Man's Do you all sit down for one meal as a family? filed value cannot be empty")
    private boolean manFamilyMeal;
    @NotNull(message = "Man's Does your diet include anything from the garden filed value cannot be empty")
    private boolean manGardenDiet;
    @NotNull(message = "Man's Are you consuming too much sugar? filed value cannot be empty")
    private boolean manMuchSugar;
    @NotNull(message = "Man's Are you eating too much fat? filed value cannot be empty")
    private boolean manMuchFat;

    @NotNull(message = "Woman's Did you have 3 main meals for the day? filed value cannot be empty")
    private boolean womenThreeMeals;
    @NotNull(message = "Woman's Animal protein filed value cannot be empty")
    private boolean womanAnimalProtein;
    @NotNull(message = "Woman's Plant protein/a grain filed value cannot be empty")
    private boolean womanPlantProtein;
    @NotNull(message = "Woman's Two types of vegetables - Daily filed value cannot be empty")
    private boolean womanVegetable;
    @NotNull(message = "Woman's One type of fruit - Daily filed value cannot be empty")
    private boolean womanFruit;
    @NotNull(message = "Woman's Do you all sit down for one meal as a family? filed value cannot be empty")
    private boolean womanFamilyMeal;
    @NotNull(message = "Woman's Does your diet include anything from the garden filed value cannot be empty")
    private boolean womanGardenDiet;
    @NotNull(message = "Woman's Are you consuming too much sugar? filed value cannot be empty")
    private boolean womanMuchSugar;
    @NotNull(message = "Woman's Are you eating too much fat? filed value cannot be empty")
    private boolean womanMuchFat;

    private String threeMealsDetails;
    private String animalProteinDetails;
    private String plantProteinDetails;
    private String vegetableDetails;
    private String fruitDetails;
    private String familyMealDetails;
    private String gardenDietDetails;
    private String muchSugarDetails;
    private String muchFatDetails;
}
