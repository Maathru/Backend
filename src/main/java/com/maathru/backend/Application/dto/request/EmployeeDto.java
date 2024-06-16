package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String phoneNumber;
    private String nic;
    private String address;
    private String gender;
    private String designation;
    private String qualifications;
    private Long userId;
}
