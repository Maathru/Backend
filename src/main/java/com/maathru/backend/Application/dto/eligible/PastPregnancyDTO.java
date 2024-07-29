package com.maathru.backend.Application.dto.eligible;

import com.maathru.backend.Domain.validation.ValidGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastPregnancyDTO {
    @ValidGender
    private String gender;
    private String result;
}
