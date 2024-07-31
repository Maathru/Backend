package com.maathru.backend.Application.dto.response;

import com.maathru.backend.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileDto {
    private long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime lastLogin;
}
