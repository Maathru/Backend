package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.DrugDto;
import com.maathru.backend.Application.dto.response.DrugResponse;
import com.maathru.backend.Domain.entity.Drug;
import com.maathru.backend.Domain.service.DrugService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drug")
@RequiredArgsConstructor
@Validated
public class DrugController {
    private final DrugService drugService;

    @PostMapping()
    public ResponseEntity<String> addDrug(@RequestBody @Valid DrugDto drugDto) {
        return drugService.addDrug(drugDto);
    }

    @GetMapping()
    public ResponseEntity<List<DrugResponse>> getAllDrugs() {
        return drugService.getAllDrugs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drug> getDrug(@PathVariable long id) {
        return drugService.getDrug(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drug> updateDrug(@PathVariable long id, @RequestBody DrugDto drugDto) {
        return drugService.updateDrug(id, drugDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Drug> deleteDrug(@PathVariable long id) {
        return drugService.deleteDrug(id);
    }
}
