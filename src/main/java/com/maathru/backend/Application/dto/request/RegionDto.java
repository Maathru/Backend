package com.maathru.backend.Application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDto {
    private long regionId;
    private String regionNumber;
    private long population;
    private String regionName;
    private long midwife;

    public RegionDto(long regionId, long population, String regionName, long midwife) {
        this.regionId = regionId;
        this.population = population;
        this.regionName = regionName;
        this.midwife = midwife;
    }
}