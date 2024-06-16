package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private User user;
}
