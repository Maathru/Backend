package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.RegionDto;
import com.maathru.backend.Domain.entity.Region;
import com.maathru.backend.Domain.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/region")
@AllArgsConstructor
public class RegionController {
}
