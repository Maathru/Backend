package com.maathru.backend.Application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
