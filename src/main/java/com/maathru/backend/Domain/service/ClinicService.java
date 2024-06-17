package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.Domain.exception.*;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Clinic> createClinic(ClinicDto clinicDto) {
        Optional<Region> optionalRegion = regionRepository.findById(clinicDto.getRegion());
        Optional<Employee> optionalEmployee = employeeRepository.findById(clinicDto.getDoctor());

        if (optionalRegion.isPresent()) {
            if (optionalEmployee.isPresent()) {
                Clinic clinic = new Clinic();
                clinic.setName(clinicDto.getName());
                clinic.setDate(clinicDto.getDate());
                clinic.setStartTime(clinicDto.getStartTime());
                clinic.setEndTime(clinicDto.getEndTime());
                clinic.setRegion(optionalRegion.get());
                clinic.setDoctor(optionalEmployee.get());

                clinic = clinicRepository.save(clinic);
                return ResponseEntity.status(201).body(clinic);
            } else {
                log.error("Doctor not found");
                throw new EmployeeNotFoundException("Doctor not found");
            }
        } else {
            log.error("Region not found");
            throw new RegionNotFoundException("Region not found");
        }
    }

    public ResponseEntity<Clinic> getClinic(Long clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);

        if (optionalClinic.isPresent()) {
            return ResponseEntity.ok(optionalClinic.get());
        } else {
            log.error("Clinic not found");
            throw new ClinicNotFoundException("Clinic not found");
        }
    }

    public ResponseEntity<Iterable<Clinic>> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();

        if (clinics.isEmpty()) {
            log.error("Clinics not found");
            throw new ClinicNotFoundException("Clinics not found");
        }
        return ResponseEntity.ok(clinics);
    }

    public ResponseEntity<Clinic> updateClinic(Long clinicId, ClinicDto clinicDto) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        Optional<Region> optionalRegion = regionRepository.findById(clinicDto.getRegion());
        Optional<Employee> optionalEmployee = employeeRepository.findById(clinicDto.getDoctor());

        if (optionalClinic.isPresent()) {
            if (optionalRegion.isPresent()) {
                if (optionalEmployee.isPresent()) {
                    Clinic clinic = optionalClinic.get();
                    clinic.setName(clinicDto.getName());
                    clinic.setDate(clinicDto.getDate());
                    clinic.setStartTime(clinicDto.getStartTime());
                    clinic.setEndTime(clinicDto.getEndTime());
                    clinic.setRegion(optionalRegion.get());
                    clinic.setDoctor(optionalEmployee.get());
                    clinic = clinicRepository.save(clinic);

                    return ResponseEntity.status(201).body(clinic);
                } else {
                    log.error("Doctor not found");
                    throw new EmployeeNotFoundException("Doctor not found");
                }
            } else {
                log.error("Region not found");
                throw new RegionNotFoundException("Region not found");
            }
        } else {
            log.error("Clinic not found");
            throw new ClinicNotFoundException("Clinic not found");
        }
    }

    public ResponseEntity<Clinic> deleteClinic(Long clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);

        if (optionalClinic.isPresent()) {
            clinicRepository.delete(optionalClinic.get());
            return ResponseEntity.ok().body(optionalClinic.get());
        } else {
            log.error("Clinic not found");
            throw new ClinicNotFoundException("Clinic not found");
        }
    }
}
