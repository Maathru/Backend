package com.maathru.backend.Application.dto.response;

import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDateTime lastLogin;

    private String phoneNumber;
    private String nic;
    private Gender gender;
    private LocalDate dob;
    private String addressLine1;
    private String street;
    private String city;
    private String designation;
    private String qualifications;
    private String moh;
    private String region;
}
