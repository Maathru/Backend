package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String phoneNumber;
    private String nic;
    private String addressLine1;
    private String street;
    private String city;
    private String designation;
    private String qualifications;
    private long user;
}
