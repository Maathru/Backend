package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
}
