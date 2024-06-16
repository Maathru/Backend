package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaccine_card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccineCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moh_id")
    private MOH moh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    private String childGender;
    private String registrationNumber;
    private String specialReasons;

    @OneToOne(mappedBy = "vaccineCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private PregnancyCard pregnancyCard;
}