package com.maathru.backend.Application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeVisitRequest {
    private long id;
    private List<VisitDto> visits;
}
