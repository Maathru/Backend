package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.DrugDto;
import com.maathru.backend.Domain.entity.Drug;
import com.maathru.backend.External.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;

    public ResponseEntity<Drug> addDrug(DrugDto drugDto){
        Drug drug = new Drug();
        drug.setBatchNumber(drugDto.getBatchNumber());
        drug.setBrandName(drugDto.getBrandName());
        drug.setComposition(drugDto.getComposition());
        drug.setExpiryDate(drugDto.getExpiryDate());
        drug.setManufacturedDate(drugDto.getManufacturedDate());
        drug.setQuantity(drugDto.getQuantity());
        drug.setReceivedDate(drugDto.getReceivedDate());
        drug.setRegisteredDate(drugDto.getRegisteredDate());
        drug.setRecommendedDose(drugDto.getRecommendedDose());
        drug.setStrength(drugDto.getStrength());
        return ResponseEntity.ok(drugRepository.save(drug));
    }

    public ResponseEntity<Iterable<Drug>> getAllDrugs(){
        return ResponseEntity.ok(drugRepository.findAll());
    }

    public ResponseEntity<Drug> getDrug(long id){
        return ResponseEntity.ok(drugRepository.findById(id).orElse(null));
    }

    public ResponseEntity<Drug> updateDrug(long id, DrugDto drugDto){
        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            return ResponseEntity.notFound().build();
        }
        drug.setBatchNumber(drugDto.getBatchNumber());
        drug.setBrandName(drugDto.getBrandName());
        drug.setComposition(drugDto.getComposition());
        drug.setExpiryDate(drugDto.getExpiryDate());
        drug.setManufacturedDate(drugDto.getManufacturedDate());
        drug.setQuantity(drugDto.getQuantity());
        drug.setReceivedDate(drugDto.getReceivedDate());
        drug.setRegisteredDate(drugDto.getRegisteredDate());
        drug.setRecommendedDose(drugDto.getRecommendedDose());
        drug.setStrength(drugDto.getStrength());
        return ResponseEntity.ok(drugRepository.save(drug));
    }

    public ResponseEntity<Drug> deleteDrug(long id){
        Drug drug = drugRepository.findById(id).orElse(null);
        if(drug == null){
            return ResponseEntity.notFound().build();
        }
        drugRepository.delete(drug);
        return ResponseEntity.ok(drug);
    }


}
