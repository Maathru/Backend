package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MidwifeResponse {
    private long id;
    private String phone;
    private String name;
    private String email;
    private String address;
}
