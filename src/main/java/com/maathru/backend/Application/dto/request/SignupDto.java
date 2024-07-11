package com.maathru.backend.Application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maathru.backend.Domain.validation.ValidRole;
import com.maathru.backend.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain both letters and digits")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @ValidRole
    private String role = Role.USER.name();;
}
