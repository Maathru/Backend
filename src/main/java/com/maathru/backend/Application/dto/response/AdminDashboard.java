package com.maathru.backend.Application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDashboard {
    private long blogsToConfirm;
    private long regions;
    private long users;
    private long thisMonthClinics;
}
