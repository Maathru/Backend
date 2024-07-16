package com.maathru.backend.Domain.entity;

import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_basic_info")
public class BasicInfo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;
    @NotNull
    private int age;
    @NotNull
    private String educationLevel;
    @NotNull
    private String occupation;
    @NotNull
    private LocalDate marriedDate;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
