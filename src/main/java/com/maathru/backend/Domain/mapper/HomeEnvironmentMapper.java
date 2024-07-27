package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.eligible.HomeEnvironmentDto;
import com.maathru.backend.Domain.entity.eligible.HomeEnvironment;

public class HomeEnvironmentMapper implements Mapper<HomeEnvironment, HomeEnvironmentDto> {
    public HomeEnvironment toEntity(HomeEnvironment homeEnvironment, HomeEnvironmentDto dto) {
        if (dto == null) {
            return null;
        }

        homeEnvironment.setManSafeWater(dto.isManSafeWater());
        homeEnvironment.setManToilet(dto.isManToilet());
        homeEnvironment.setManGarbage(dto.isManGarbage());
        homeEnvironment.setManHazardous(dto.isManHazardous());
        homeEnvironment.setManChemicals(dto.isManChemicals());
        homeEnvironment.setManRadioactive(dto.isManRadioactive());
        homeEnvironment.setManExtremeHeat(dto.isManExtremeHeat());
        homeEnvironment.setManSmokeCooking(dto.isManSmokeCooking());
        homeEnvironment.setManNoisy(dto.isManNoisy());
        homeEnvironment.setManSaving(dto.isManSaving());

        homeEnvironment.setWomanSafeWater(dto.isWomanSafeWater());
        homeEnvironment.setWomanToilet(dto.isWomanToilet());
        homeEnvironment.setWomanGarbage(dto.isWomanGarbage());
        homeEnvironment.setWomanHazardous(dto.isWomanHazardous());
        homeEnvironment.setWomanChemicals(dto.isWomanChemicals());
        homeEnvironment.setWomanRadioactive(dto.isWomanRadioactive());
        homeEnvironment.setWomanExtremeHeat(dto.isWomanExtremeHeat());
        homeEnvironment.setWomanSmokeCooking(dto.isWomanSmokeCooking());
        homeEnvironment.setWomanNoisy(dto.isWomanNoisy());
        homeEnvironment.setWomanSaving(dto.isWomanSaving());

        homeEnvironment.setWaterDetails(dto.getWaterDetails());
        homeEnvironment.setToiletDetails(dto.getToiletDetails());
        homeEnvironment.setGarbageDetails(dto.getGarbageDetails());
        homeEnvironment.setHazardousDetails(dto.getHazardousDetails());
        homeEnvironment.setChemicalsDetails(dto.getChemicalsDetails());
        homeEnvironment.setRadioactiveDetails(dto.getRadioactiveDetails());
        homeEnvironment.setExtremeHeatDetails(dto.getExtremeHeatDetails());
        homeEnvironment.setSmokeCookingDetails(dto.getSmokeCookingDetails());
        homeEnvironment.setNoisyDetails(dto.getNoisyDetails());
        homeEnvironment.setSavingDetails(dto.getSavingDetails());

        return homeEnvironment;
    }
}