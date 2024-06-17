package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.AppointmentDto;
import com.maathru.backend.Domain.entity.Appointment;
import com.maathru.backend.Domain.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<Appointment> createAppointment(AppointmentDto appointmentDto) {
        return appointmentService.createAppointment(appointmentDto);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(Long appointmentId, AppointmentDto appointmentDto) {
        return appointmentService.updateAppointment(appointmentId, appointmentDto);
    }
}
