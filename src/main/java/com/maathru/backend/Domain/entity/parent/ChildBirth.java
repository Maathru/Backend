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
@Table(name = "child_births")
public class ChildBirth extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private String birthPlace;
    private LocalDate dob;
    private String dateReleased;
    private String doneBy;
    private String resultOfPregnancy;
    private String statusOfPregnancy;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
