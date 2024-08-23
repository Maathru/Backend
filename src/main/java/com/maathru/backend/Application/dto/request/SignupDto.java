package com.maathru.backend.Application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maathru.backend.Domain.validation.ValidPassword;
import com.maathru.backend.Domain.validation.ValidRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SignupDto {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @ValidPassword
    private String password;

    @ValidRole
    private String role;
}
