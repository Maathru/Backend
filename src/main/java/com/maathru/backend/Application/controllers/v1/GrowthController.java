package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.parent.ParentDetailsDto;
import com.maathru.backend.Application.dto.response.PregnancyCardForGrowthResponse;
import com.maathru.backend.Domain.entity.PregnancyCard;
import com.maathru.backend.Domain.service.GrowthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/growth")
@RequiredArgsConstructor
@Validated
public class GrowthController {
    private final GrowthService growthService;

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/dob")
    public ResponseEntity<LocalDate> getDob() {
        return growthService.getDob();
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/dop")
    public ResponseEntity<LocalDate> getDop() {
        return growthService.getDop();
    }


}
