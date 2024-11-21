package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pregnancy_histories")
public class PregnancyHistory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private int pregnanciesG;
    private int pregnanciesP;
    private LocalDate youngestChildBirthday;

    private LocalDate lastMenstrualPeriod;
    private LocalDate expectedDoD;
    private LocalDate usCorrectedExpectedDoD;
    private LocalDate expectedPeriodBegin;
    private LocalDate expectedPeriodEnd;
    private LocalDate firstSensationOfRotationalMotion;
    private int gestationalWeeksAtEnrollment;
    private boolean familyPlanningMethodsBeforePregnancy;
    private String familyPlanningMethodsBeforePregnancyDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
