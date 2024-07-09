package com.maathru.backend.Application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
