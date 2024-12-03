package com.maathru.backend.Application.dto.response;

import com.maathru.backend.enumeration.VisitStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class HomeVisitsListResponse {
    private long id;
    private LocalDate date;
    private LocalTime time;
    private String motherName;
    private String address;
    private VisitStatus visitStatus;

    public HomeVisitsListResponse(long id, LocalTime time, String motherName, String address, VisitStatus visitStatus) {
        this.id = id;
        this.time = time;
        this.motherName = motherName;
        this.address = address;
        this.visitStatus = visitStatus;
    }
}
