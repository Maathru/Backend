package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String nic;
    private String gender;
    private LocalDate dob;
    private String addressLine1;
    private String street;
    private String city;
    private String designation;
    private String qualifications;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}