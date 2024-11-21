package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "clinics")
@Getter
@Setter
public class Clinic extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long clinicId;

    @NotNull
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;

    private String other;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinic_doctors",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Employee> doctors;

    @ManyToOne
    @JoinColumn(name = "moh_id")
    private MOH moh;

    @ManyToOne()
    @JoinColumn(name = "region_id")
    private Region region;
}