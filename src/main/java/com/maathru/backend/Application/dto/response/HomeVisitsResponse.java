package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeVisitsResponse {
    private String motherName;
    private String fatherName;
    private String address;
    private String motherPhone;
    private String fatherPhone;
    private String location;

    private List<HomeVisitsListResponse> visits;

    public HomeVisitsResponse(String motherName, String fatherName, String address, String motherPhone, String fatherPhone, String location) {
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.address = address;
        this.motherPhone = motherPhone;
        this.fatherPhone = fatherPhone;
        this.location = location;
    }
}
