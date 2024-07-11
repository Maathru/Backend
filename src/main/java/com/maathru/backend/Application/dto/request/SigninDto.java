package com.maathru.backend.Application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SigninDto {
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
