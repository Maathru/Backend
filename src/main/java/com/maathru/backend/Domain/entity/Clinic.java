package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;

    @Column(name = "clinic_name")
    private String clinicName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "region_region_id")
    private Region region;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "doctor_employee_id")
    private Employee doctor;

    @ManyToOne
    @JoinColumn(name = "admin_employee_id")
    private Employee admin;

}