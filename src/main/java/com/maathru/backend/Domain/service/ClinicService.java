package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.ClinicDto;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;

    public Clinic createClinic(ClinicDto clinicDto) {
        Clinic clinic = new Clinic();
//        clinic.setClinicName(clinicDto.getClinicName());
        clinic.setDate(clinicDto.getDate());
        clinic.setStartTime(clinicDto.getStartTime());
        clinic.setEndTime(clinicDto.getEndTime());
        clinic.setRegion(regionRepository.findById(clinicDto.getRegionId()).orElseThrow(() -> new RuntimeException("Region not found")));
        clinic.setDoctor(employeeRepository.findById(clinicDto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found")));
//        clinic.setAdmin(employeeRepository.findById(clinicDto.getAdminId()).orElseThrow(() -> new RuntimeException("Admin not found")));
        return clinicRepository.save(clinic);
    }

    public Clinic getClinic(Long clinicId) {
        return clinicRepository.findById(clinicId).orElseThrow(() -> new RuntimeException("Clinic not found"));
    }

    public Iterable<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic updateClinic(Long clinicId, ClinicDto clinicDto) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(() -> new RuntimeException("Clinic not found"));
//        clinic.setClinicName(clinicDto.getClinicName());
        clinic.setDate(clinicDto.getDate());
        clinic.setStartTime(clinicDto.getStartTime());
        clinic.setEndTime(clinicDto.getEndTime());
        clinic.setRegion(regionRepository.findById(clinicDto.getRegionId()).orElseThrow(() -> new RuntimeException("Region not found")));
        clinic.setDoctor(employeeRepository.findById(clinicDto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found")));
//        clinic.setAdmin(employeeRepository.findById(clinicDto.getAdminId()).orElseThrow(() -> new RuntimeException("Admin not found")));
        return clinicRepository.save(clinic);
    }

    public void deleteClinic(Long clinicId) {
        clinicRepository.deleteById(clinicId);
    }
}
