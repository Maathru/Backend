package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RegionResponse {
    private Long regionId;
    private String regionName;
    private Long population;
    private String midwife;

    public RegionResponse(Long regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }
}
