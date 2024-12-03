package com.maathru.backend.Domain.entity;

import com.maathru.backend.enumeration.VisitStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
@Getter
@Setter
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private LocalTime time;
    private VisitStatus status;

    @ManyToOne
    @JoinColumn(name = "home_visit_id")
    private HomeVisit homeVisit;
}
