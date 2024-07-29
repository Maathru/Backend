package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Domain.entity.eligible.FamilyNutrition;
import com.maathru.backend.Application.dto.eligible.FamilyNutritionDto;

public class FamilyNutritionMapper implements Mapper<FamilyNutrition, FamilyNutritionDto> {

    public static FamilyNutritionDto toDto(FamilyNutrition familyNutrition) {
        if (familyNutrition == null) {
            return null;
        }

        FamilyNutritionDto dto = new FamilyNutritionDto();

        dto.setManThreeMeals(familyNutrition.isManThreeMeals());
        dto.setManAnimalProtein(familyNutrition.isManAnimalProtein());
        dto.setManPlantProtein(familyNutrition.isManPlantProtein());
        dto.setManVegetable(familyNutrition.isManVegetable());
        dto.setManFruit(familyNutrition.isManFruit());
        dto.setManFamilyMeal(familyNutrition.isManFamilyMeal());
        dto.setManGardenDiet(familyNutrition.isManGardenDiet());
        dto.setManMuchSugar(familyNutrition.isManMuchSugar());
        dto.setManMuchFat(familyNutrition.isManMuchFat());

        dto.setWomenThreeMeals(familyNutrition.isWomenThreeMeals());
        dto.setWomanAnimalProtein(familyNutrition.isWomanAnimalProtein());
        dto.setWomanPlantProtein(familyNutrition.isWomanPlantProtein());
        dto.setWomanVegetable(familyNutrition.isWomanVegetable());
        dto.setWomanFruit(familyNutrition.isWomanFruit());
        dto.setWomanFamilyMeal(familyNutrition.isWomanFamilyMeal());
        dto.setWomanGardenDiet(familyNutrition.isWomanGardenDiet());
        dto.setWomanMuchSugar(familyNutrition.isWomanMuchSugar());
        dto.setWomanMuchFat(familyNutrition.isWomanMuchFat());

        dto.setThreeMealsDetails(familyNutrition.getThreeMealsDetails());
        dto.setAnimalProteinDetails(familyNutrition.getAnimalProteinDetails());
        dto.setPlantProteinDetails(familyNutrition.getPlantProteinDetails());
        dto.setVegetableDetails(familyNutrition.getVegetableDetails());
        dto.setFruitDetails(familyNutrition.getFruitDetails());
        dto.setFamilyMealDetails(familyNutrition.getFamilyMealDetails());
        dto.setGardenDietDetails(familyNutrition.getGardenDietDetails());
        dto.setMuchSugarDetails(familyNutrition.getMuchSugarDetails());
        dto.setMuchFatDetails(familyNutrition.getMuchFatDetails());

        return dto;
    }

    public FamilyNutrition toEntity(FamilyNutrition familyNutrition, FamilyNutritionDto dto) {
        if (dto == null) {
            return null;
        }

        familyNutrition.setManThreeMeals(dto.isManThreeMeals());
        familyNutrition.setManAnimalProtein(dto.isManAnimalProtein());
        familyNutrition.setManPlantProtein(dto.isManPlantProtein());
        familyNutrition.setManVegetable(dto.isManVegetable());
        familyNutrition.setManFruit(dto.isManFruit());
        familyNutrition.setManFamilyMeal(dto.isManFamilyMeal());
        familyNutrition.setManGardenDiet(dto.isManGardenDiet());
        familyNutrition.setManMuchSugar(dto.isManMuchSugar());
        familyNutrition.setManMuchFat(dto.isManMuchFat());

        familyNutrition.setWomenThreeMeals(dto.isWomenThreeMeals());
        familyNutrition.setWomanAnimalProtein(dto.isWomanAnimalProtein());
        familyNutrition.setWomanPlantProtein(dto.isWomanPlantProtein());
        familyNutrition.setWomanVegetable(dto.isWomanVegetable());
        familyNutrition.setWomanFruit(dto.isWomanFruit());
        familyNutrition.setWomanFamilyMeal(dto.isWomanFamilyMeal());
        familyNutrition.setWomanGardenDiet(dto.isWomanGardenDiet());
        familyNutrition.setWomanMuchSugar(dto.isWomanMuchSugar());
        familyNutrition.setWomanMuchFat(dto.isWomanMuchFat());

        familyNutrition.setThreeMealsDetails(dto.getThreeMealsDetails());
        familyNutrition.setAnimalProteinDetails(dto.getAnimalProteinDetails());
        familyNutrition.setPlantProteinDetails(dto.getPlantProteinDetails());
        familyNutrition.setVegetableDetails(dto.getVegetableDetails());
        familyNutrition.setFruitDetails(dto.getFruitDetails());
        familyNutrition.setFamilyMealDetails(dto.getFamilyMealDetails());
        familyNutrition.setGardenDietDetails(dto.getGardenDietDetails());
        familyNutrition.setMuchSugarDetails(dto.getMuchSugarDetails());
        familyNutrition.setMuchFatDetails(dto.getMuchFatDetails());

        return familyNutrition;
    }
}