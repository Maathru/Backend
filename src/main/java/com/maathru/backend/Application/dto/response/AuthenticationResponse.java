package com.maathru.backend.Application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maathru.backend.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private Long id;
    private String name;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private Role Role;
    private String message;

    public AuthenticationResponse(Long id, String name, String accessToken, String refreshToken, com.maathru.backend.enumeration.Role role, String message) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        Role = role;
        this.message = message;
    }

    public AuthenticationResponse(String accessToken, String refreshToken, String message) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }

    public AuthenticationResponse(String message) {
        this.message = message;
    }
}
