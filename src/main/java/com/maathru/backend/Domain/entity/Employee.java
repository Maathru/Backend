package com.maathru.backend.Domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "nic", nullable = false, unique = true)
    private String nic;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "designation")
    private String designation;

    @Column(name = "qualifications")
    private String qualifications;

    @OneToOne(cascade = CascadeType.DETACH, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

}
