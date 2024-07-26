package com.maathru.backend.Domain.entity;

import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "family_planning_methods")
public class FamilyPlanningMethod extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private LocalDate date;
    private String method;

    @ManyToOne
    @JoinColumn(name = "basic_info_id")
    private BasicInfo basicInfo;
}
