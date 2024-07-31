package com.maathru.backend.Domain.entity;

import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long employeeId;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String nic;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;
    private String addressLine1;
    private String street;
    private String city;
    private String designation;
    private String qualifications;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "moh_moh_id")
    private MOH moh;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public Employee(Long employeeId, String phoneNumber, String nic, Gender gender, LocalDate dob, String addressLine1, String street, String city, String designation, String qualifications, User user, MOH moh) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
        this.gender = gender;
        this.dob = dob;
        this.addressLine1 = addressLine1;
        this.street = street;
        this.city = city;
        this.designation = designation;
        this.qualifications = qualifications;
        this.user = user;
        this.moh = moh;
    }

    public Employee() {
    }
}