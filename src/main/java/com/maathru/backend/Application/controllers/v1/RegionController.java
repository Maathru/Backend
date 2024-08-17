package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Application.dto.response.DrugResponse;
import com.maathru.backend.Application.dto.response.RegionResponse;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
@AllArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping()
    public ResponseEntity<List<RegionResponse>> getAllRegions() {
        return regionService.getAllRegions();
    }
}
