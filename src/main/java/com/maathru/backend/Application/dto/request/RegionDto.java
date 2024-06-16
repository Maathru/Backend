package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegionDto {
    private Long regionId;
    private String regionNumber;
    private String regionName;
    private Long population;
    private Long mohMohId;
    private List<Long> midwifeId;
}
