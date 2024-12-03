package com.maathru.backend.Application.dto.response;

import com.maathru.backend.enumeration.Role;
import com.maathru.backend.enumeration.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatUserDto {
    private long userId;
    private String name;
    private Role  role;
    private Status status;
    private LocalDateTime lastSeen;
}
