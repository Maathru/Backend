package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "family_nutrition")
public class FamilyNutrition extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean manThreeMeals;
    @NotNull
    private boolean manAnimalProtein;
    @NotNull
    private boolean manPlantProtein;
    @NotNull
    private boolean manVegetable;
    @NotNull
    private boolean manFruit;
    @NotNull
    private boolean manFamilyMeal;
    @NotNull
    private boolean manGardenDiet;
    @NotNull
    private boolean manMuchSugar;
    @NotNull
    private boolean manMuchFat;

    @NotNull
    private boolean womenThreeMeals;
    @NotNull
    private boolean womanAnimalProtein;
    @NotNull
    private boolean womanPlantProtein;
    @NotNull
    private boolean womanVegetable;
    @NotNull
    private boolean womanFruit;
    @NotNull
    private boolean womanFamilyMeal;
    @NotNull
    private boolean womanGardenDiet;
    @NotNull
    private boolean womanMuchSugar;
    @NotNull
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

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
