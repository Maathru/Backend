package com.maathru.backend.Application.dto.response;

import com.maathru.backend.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EligibleCoupleResponse {
    private long id;
    private String womanName;
    private String manName;
    private String address;
    private String womanPhone;
    private String manPhone;
    private LocalDate womanDob;
    private LocalDate manDob;
    private int children;
    private Role role;
}
