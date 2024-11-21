package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AppointmentDto;
import com.maathru.backend.Domain.entity.Appointment;
import com.maathru.backend.Domain.entity.Clinic;
import com.maathru.backend.Domain.entity.Employee;
import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.AppointmentRepository;
import com.maathru.backend.External.repository.ClinicRepository;
import com.maathru.backend.External.repository.EmployeeRepository;
import com.maathru.backend.External.repository.ParentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ClinicRepository clinicRepository;
    private final ParentRepository parentRepository;

    public ResponseEntity<Appointment> createAppointment(AppointmentDto appointmentDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(appointmentDto.getDoctor());
        Optional<Clinic> optionalClinic = clinicRepository.findById(appointmentDto.getClinic());
        Optional<Parent> optionalParent = parentRepository.findById(appointmentDto.getParent());

        if (optionalEmployee.isPresent()) {
            if (optionalClinic.isPresent()) {
                if (optionalParent.isPresent()) {
                    Appointment appointment = new Appointment();
                    appointment.setAssignedTime(appointmentDto.getAssignedTime());
                    appointment.setDoctor(optionalEmployee.get());
                    appointment.setClinic(optionalClinic.get());
                    appointment.setParent(optionalParent.get());
                    appointmentRepository.save(appointment);

                    return ResponseEntity.status(201).body(appointment);
                } else {
                    log.error("Parent not found");
                    throw new NotFoundException("Parent not found");
                }
            } else {
                log.error("Clinic not found");
                throw new NotFoundException("Clinic not found");
            }
        } else {
            log.error("Doctor not found");
            throw new NotFoundException("Doctor not found");
        }
    }

    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.isEmpty()) {
            log.error("Appointments not found");
            throw new NotFoundException("Appointments not found");
        }

        return ResponseEntity.ok(appointments);
    }


    public ResponseEntity<Appointment> getAppointmentById(Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        if (optionalAppointment.isPresent()) {
            return ResponseEntity.ok(optionalAppointment.get());
        } else {
            log.error("Appointment not found");
            throw new NotFoundException("Appointment not found");
        }
    }

    public ResponseEntity<Appointment> updateAppointment(Long appointmentId, AppointmentDto appointmentDto) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        Optional<Employee> optionalEmployee = employeeRepository.findById(appointmentDto.getDoctor());
        Optional<Clinic> optionalClinic = clinicRepository.findById(appointmentDto.getClinic());
        Optional<Parent> optionalParent = parentRepository.findById(appointmentDto.getParent());

        if (optionalAppointment.isPresent()) {
            if (optionalEmployee.isPresent()) {
                if (optionalClinic.isPresent()) {
                    if (optionalParent.isPresent()) {
                        Appointment appointment = optionalAppointment.get();

                        appointment.setAssignedTime(appointmentDto.getAssignedTime());
                        appointment.setDoctor(optionalEmployee.get());
                        appointment.setClinic(optionalClinic.get());
                        appointment.setParent(optionalParent.get());
                        appointmentRepository.save(appointment);

                        return ResponseEntity.status(201).body(appointment);
                    } else {
                        log.error("Parent not found");
                        throw new NotFoundException("Parent not found");
                    }
                } else {
                    log.error("Clinic not found");
                    throw new NotFoundException("Clinic not found");
                }
            } else {
                log.error("Doctor not found");
                throw new NotFoundException("Doctor not found");
            }
        } else {
            log.error("Appointment not found");
            throw new NotFoundException("Appointment not found");
        }
    }
}
