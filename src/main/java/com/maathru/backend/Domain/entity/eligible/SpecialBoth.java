package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "special_both")
public class SpecialBoth extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean manDissatisfiedSex;
    @NotNull
    private boolean manFamilyPlaning;
    @NotNull
    private boolean manDelayFirstBirth;

    @NotNull
    private boolean womanDissatisfiedSex;
    @NotNull
    private boolean womanFamilyPlaning;
    @NotNull
    private boolean womanDelayFirstBirth;
    @NotNull
    private boolean manBreastExamination;

    private String dissatisfiedSexDetails;
    private String familyPlaningDetails;
    private String delayFirstBirthDetails;
    private String breastExaminationDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
