package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vaccine_card")
public class VaccineCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_card_id", nullable = false)
    private Long vaccineCardId;

    @ManyToOne
    @JoinColumn(name = "moh_moh_id")
    private MOH moh;

    @ManyToOne
    @JoinColumn(name = "parent_parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "region_region_id")
    private Region region;

    @Column(name = "gender")
    private String gender;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "special_reasons")
    private String specialReasons;

    // add pregnancy card

}