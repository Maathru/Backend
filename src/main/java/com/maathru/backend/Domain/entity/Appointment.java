package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "assigned_time")
    private LocalTime assignedTime;

    @Column(name = "completed_time")
    private LocalTime completedTime;

    @Column(name = "completed_status")
    private String completedStatus;

    @ManyToOne
    @JoinColumn(name = "updated_by_employee_id")
    private Employee updatedBy;

    @ManyToOne
    @JoinColumn(name = "doctor_employee_id")
    private Employee doctor;

    @ManyToOne
    @JoinColumn(name = "midwife_employee_id")
    private Employee midwife;

    @ManyToOne
    @JoinColumn(name = "clinic_clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "admin_employee_id")
    private Employee admin;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "parent_parent_id")
    private Parent parent;

}