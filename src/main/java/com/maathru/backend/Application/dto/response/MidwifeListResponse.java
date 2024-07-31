package com.maathru.backend.Application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MidwifeListResponse {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String region;
}
