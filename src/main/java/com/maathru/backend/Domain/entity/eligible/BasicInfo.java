package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
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
    private int womanAge;
    @NotNull
    private int manAge;
    @NotNull
    private String womanEducationLevel;
    @NotNull
    private String manEducationLevel;
    private String womanOccupation;
    private String manOccupation;
    @NotNull
    private LocalDate marriedDate;
    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
