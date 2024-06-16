package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.MOH;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegionDto {
    private String regionNumber;
    private String regionName;
    private Long population;
    private MOH moh;
    private List<Employee> midwives = new ArrayList<>();
}