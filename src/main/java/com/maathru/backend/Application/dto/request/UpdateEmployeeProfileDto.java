package com.maathru.backend.Application.dto.request;

import com.maathru.backend.Domain.validation.ValidNIC;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeProfileDto {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Invalid phone number format")
    private String phoneNumber;
    @NotEmpty(message = "NIC cannot be empty")
    @ValidNIC
    private String nic;
    @NotEmpty(message = "Address line 1 cannot be empty")
    private String addressLine1;
    @NotEmpty(message = "Street cannot be empty")
    private String street;
    @NotEmpty(message = "City cannot be empty")
    private String city;
    @NotEmpty(message = "Designation cannot be empty")
    private String designation;
    private String qualifications;
}
