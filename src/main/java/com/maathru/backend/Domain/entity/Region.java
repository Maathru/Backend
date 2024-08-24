package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class Region extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long regionId;

    @Column(unique = true)
    private String regionNumber;
    private String regionName;
    private long population;

    @ManyToOne
    @JoinColumn(name = "moh_id")
    private MOH moh;

    public Region(Long regionId, String regionNumber, String regionName, Long population, MOH moh) {
        this.regionId = regionId;
        this.regionNumber = regionNumber;
        this.regionName = regionName;
        this.population = population;
        this.moh = moh;
    }

    public Region() {

    }
}