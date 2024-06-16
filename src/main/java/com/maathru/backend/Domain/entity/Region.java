package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id", nullable = false)
    private Long regionId;

    @Column(name = "region_number", unique = true)
    private String regionNumber;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "population")
    private Long population;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "moh_moh_id")
    private MOH moh;

    @ElementCollection
    @Column(name = "midwife")
    @CollectionTable(name = "region_midwives", joinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> midwives = new ArrayList<>();
}