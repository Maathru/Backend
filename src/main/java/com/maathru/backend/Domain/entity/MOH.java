package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moh")
public class MOH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moh_id", nullable = false)
    private Long mohId;

    @Column(name = "division")
    private String division;

    @Column(name = "moh_registration_number", nullable = false, unique = true)
    private String mohRegistrationNumber;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "population")
    private Long population;

    @OneToOne(cascade = CascadeType.DETACH, orphanRemoval = true)
    @JoinColumn(name = "current_head_employee_id")
    private Employee currentHead;



}