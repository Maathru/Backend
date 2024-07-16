package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.DrugDto;
import com.maathru.backend.Application.dto.response.DrugResponse;
import com.maathru.backend.Domain.entity.Drug;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.DrugNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.DrugRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugService {
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ResponseEntity<String> addDrug(DrugDto drugDto) {
        User currentUser = getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("Author not found");
        }

        Drug drug = mapper.map(drugDto, Drug.class);
        drug.setCreatedBy(currentUser);
        drug.setUpdatedBy(currentUser);

        drug = drugRepository.save(drug);
        log.info("Drug:{} added successfully by {}", drug.getDrugId(), currentUser.getEmail());
        return ResponseEntity.status(201).body("Drug added successfully");
    }

    public ResponseEntity<List<DrugResponse>> getAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();

        if (drugs.isEmpty()) {
            throw new DrugNotFoundException("Drugs not found");
        }
        return ResponseEntity.ok(drugs.stream().map(drug -> mapper.map(drug, DrugResponse.class)).collect(Collectors.toList()));
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

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            return optionalUser.orElseGet(User::new);
        }
        return new User();
    }
}