package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AppointmentDto;
import com.maathru.backend.Domain.entity.Appointment;
import com.maathru.backend.External.repository.AppointmentRepository;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ClinicRepository clinicRepository;
    private final ParentRepository parentRepository;


    public ResponseEntity<Appointment> createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
//        appointment.setAssignedTime(appointmentDto.getAssignedTime());
//        appointment.setCompletedTime(appointmentDto.getCompletedTime());
//        appointment.setCompletedStatus(appointmentDto.getCompletedStatus());
        appointment.setUpdatedBy(employeeRepository.findById(appointmentDto.getUpdatedByEmployeeId()).orElse(null));
        appointment.setDoctor(employeeRepository.findById(appointmentDto.getDoctorEmployeeId()).orElse(null));
//        appointment.setMidwife(employeeRepository.findById(appointmentDto.getMidwifeEmployeeId()).orElse(null));
        appointment.setClinic(clinicRepository.findById(appointmentDto.getClinicClinicId()).orElse(null));
//        appointment.setAdmin(employeeRepository.findById(appointmentDto.getAdminEmployeeId()).orElse(null));
        appointment.setParent(parentRepository.findById(appointmentDto.getParentParentId()).orElse(null));
        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }

    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentRepository.findAll());
    }


    public ResponseEntity<Appointment> getAppointmentById(Long appointmentId) {
        return ResponseEntity.ok(appointmentRepository.findById(appointmentId).orElse(null));
    }

    public ResponseEntity<Appointment> updateAppointment(Long appointmentId, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
//        appointment.setAssignedTime(appointmentDto.getAssignedTime());
//        appointment.setCompletedTime(appointmentDto.getCompletedTime());
//        appointment.setCompletedStatus(appointmentDto.getCompletedStatus());
        appointment.setUpdatedBy(employeeRepository.findById(appointmentDto.getUpdatedByEmployeeId()).orElse(null));
        appointment.setDoctor(employeeRepository.findById(appointmentDto.getDoctorEmployeeId()).orElse(null));
//        appointment.setMidwife(employeeRepository.findById(appointmentDto.getMidwifeEmployeeId()).orElse(null));
        appointment.setClinic(clinicRepository.findById(appointmentDto.getClinicClinicId()).orElse(null));
//        appointment.setAdmin(employeeRepository.findById(appointmentDto.getAdminEmployeeId()).orElse(null));
        appointment.setParent(parentRepository.findById(appointmentDto.getParentParentId()).orElse(null));

        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }
}
