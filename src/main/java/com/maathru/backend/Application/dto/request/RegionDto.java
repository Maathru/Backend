package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDto {
    private String regionNumber;
    private String regionName;
    private Long population;
    private Long midwife;
}