package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.DrugDto;
import com.maathru.backend.Domain.entity.Drug;
import com.maathru.backend.Domain.exception.DrugNotFoundException;
import com.maathru.backend.External.repository.DrugRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DrugService {
    private final DrugRepository drugRepository;

    public ResponseEntity<Drug> addDrug(DrugDto drugDto) {
        Drug drug = new Drug();
        drug.setComposition(drugDto.getComposition());
        drug.setStrength(drugDto.getStrength());
        drug.setBrandName(drugDto.getBrandName());
        drug.setQuantity(drugDto.getQuantity());
        drug.setBatchNumber(drugDto.getBatchNumber());
        drug.setRecommendedDose(drugDto.getRecommendedDose());
        drug.setExpiryDate(drugDto.getExpiryDate());
        drug.setManufacturedDate(drugDto.getManufacturedDate());
        drug.setReceivedDate(drugDto.getReceivedDate());

        drug = drugRepository.save(drug);
        return ResponseEntity.status(201).body(drug);
    }

    public ResponseEntity<Iterable<Drug>> getAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();

        if (drugs.isEmpty()) {
            log.error("Drugs not found");
            throw new DrugNotFoundException("Drugs not found");
        }
        return ResponseEntity.ok(drugs);
    }

    public ResponseEntity<Drug> getDrug(long id) {
        Optional<Drug> optionalDrug = drugRepository.findById(id);

        if (optionalDrug.isPresent()) {
            return ResponseEntity.ok(optionalDrug.get());
        } else {
            log.error("Drug not found");
            throw new DrugNotFoundException("Drug not found");
        }
    }

    public ResponseEntity<Drug> updateDrug(long id, DrugDto drugDto) {
        Optional<Drug> optionalDrug = drugRepository.findById(id);

        if (optionalDrug.isPresent()) {
            Drug drug = optionalDrug.get();
            drug.setComposition(drugDto.getComposition());
            drug.setStrength(drugDto.getStrength());
            drug.setBrandName(drugDto.getBrandName());
            drug.setQuantity(drugDto.getQuantity());
            drug.setBatchNumber(drugDto.getBatchNumber());
            drug.setRecommendedDose(drugDto.getRecommendedDose());
            drug.setExpiryDate(drugDto.getExpiryDate());
            drug.setManufacturedDate(drugDto.getManufacturedDate());
            drug.setReceivedDate(drugDto.getReceivedDate());

            drug = drugRepository.save(drug);
            return ResponseEntity.status(201).body(drug);
        } else {
            log.error("Drug not found");
            throw new DrugNotFoundException("Drug not found");
        }
    }

    public ResponseEntity<Drug> deleteDrug(long id) {
        Optional<Drug> optionalDrug = drugRepository.findById(id);

        if (optionalDrug.isPresent()) {
            drugRepository.delete(optionalDrug.get());
            return ResponseEntity.ok(optionalDrug.get());
        } else {
            log.error("Drug not found");
            throw new DrugNotFoundException("Drug not found");
        }
    }
}