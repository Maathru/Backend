package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "child_details")
public class ChildDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private Gender sex;
    private float birthWeight;
    private boolean prematureBirth;
    private boolean complicationsAtBirth;

    private boolean motherDead;
    private LocalDate motherDeadDate;
    private String motherDeadCause;
    private boolean investigated;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
