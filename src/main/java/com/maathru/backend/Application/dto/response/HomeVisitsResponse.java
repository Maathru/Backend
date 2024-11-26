package com.maathru.backend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HomeVisitsResponse {
    private String motherName;
    private String fatherName;
    private String address;
    private String motherPhone;
    private String fatherPhone;
    private String location;
}
